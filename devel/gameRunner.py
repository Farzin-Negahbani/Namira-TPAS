import json
from os import system , listdir,system, chdir, getcwd
import threading
import time
import multiprocessing 
import socket
import sys
from os.path import expanduser,isdir
#import RCGPyParser, logAnalyzer



#def parse_log():
    

#def update_result(left, leftgoal, right, rightgoal, round, path):
#    
#    chdir(path)
#    if(round == 1):
#         gameresult = open('gameresult.txt','w')
#    else:
#         gameresult = open('gameresult.txt','a')
#   
#    gameresult.write("#,"+ str(round)+','+ left.team_name +','+ str(leftgoal)+','+ right.team_name +','+ str(rightgoal)+'\n')
#    gameresult.close()


#def print(s):
#   chdir(path)
#   f = open("log.txt",'a')
#   f.write(str(s)+'\n')
#   f.close()'

def set_server_parameter(data, parameter, value):
    for line in data:
        if( ('#' not in line) and ("server::"+parameter) in line ):
             data[data.index(line)] = "server::"+parameter+" = "+str(value)+'\n'
             print(line + "  replaced")
             return data
    
def set_server_params(parsed_json):
    file = open(expanduser("~") +'/.rcssserver/server.conf','r')
    # read all of lines into data
    data = file.readlines()
    file.close()
    
    data = set_server_parameter(data, 'stamina_capacity', parsed_json['stamina_capacity'])
    data = set_server_parameter(data, 'use_offside', 'true' if parsed_json['use_offside'] else 'false')
    data = set_server_parameter(data, 'golden_goal', 'true' if parsed_json['golden_goal'] else 'false')
    data = set_server_parameter(data, 'penalty_shoot_outs', 'true' if parsed_json['penalty_shoot_outs'] else 'false')
    data = set_server_parameter(data, 'half_time', parsed_json['half_time'])
    data = set_server_parameter(data, 'extra_half_time', parsed_json['extra_half_time'])
    data = set_server_parameter(data, 'synch_mode', 'true' if parsed_json['synch_mode'] else 'false')
    
    # and write everything back
    file = open(expanduser("~") +'/.rcssserver/server.conf', 'w')
    file.writelines( data )
    file.close()


def get_name(path):
    temp = open(path, 'r')

    for line in temp:
        if ("teamname=" in line ):
            temp.close()
            return line.split("\"")[1]
        
    t = path.split("/")[-1]
    s = path.replace(t,"")
    temp = open(s+"start", 'r')
    for line in temp:
        if ("teamname=" in line ):
            temp.close()
            return line.split("\"")[1]
    
    return "notFOUND"

    
class Game:
    def __init__(self, right_team, left_team, round_count):
        self.right_team = right_team
        self.left_team  = left_team
        self.round_count  = round_count  
    
#    def to_dic(self):
#        
#        "{0}: Wins:{1}, Losses:{2}, Draws:{3}, GF:{4}, GA:{5}, GD:{6}, PTS:{7}\n".format(self.team_name, self.wins, self.losses, self.draws, self.goals_for, self.goals_against, self.goals_difference, self.points)
#    


class Team:
    def __init__(self, team_name, path, starter):
        self.team_name = team_name
        self.wins = 0
        self.losses = 0
        self.draws = 0
        self.goals_for = 0
        self.goals_against = 0
        self.goals_difference = 0
        self.points = 0
        self.path  = path
        self.starter = starter

    def play(self,goals_for, goals_against):
        if goals_for > goals_against :
            self.wins += 1
            self.points += 3
        elif goals_for < goals_against:
            self.losses += 1
        else:
            self.draws += 1
            self.points += 1
            
        self.goals_for += goals_for
        self.goals_against += goals_against
        self.goals_difference = self.goals_for - self.goals_against
    


    def __str__(self):
        return "{0}: Wins:{1}, Losses:{2}, Draws:{3}, GF:{4}, GA:{5}, GD:{6}, PTS:{7}\n".format(self.team_name, self.wins, self.losses, self.draws, self.goals_for, self.goals_against, self.goals_difference, self.points)

class TeamLoader(threading.Thread):
    
    def __init__(self, path, starter):
        threading.Thread.__init__(self)
        self.path = path
        self.starter = starter
        
    def run(self):
        chdir(self.path)
        system('./'+self.starter)


class ServerLoader(threading.Thread):
    def __init__(self, address):
        threading.Thread.__init__(self)
        self.path = address
        chdir(self.path)
        
    def run(self):
        chdir(self.path)
        system('rcssserver')

class MonitorLoader(multiprocessing.Process):
    
    def __init__(self):
        multiprocessing.Process.__init__(self)
        
    def run(self):
        system('rcssmonitor')


def play_game(address, team_a, team_b, monitorOn):
    server = ServerLoader(address)
    server.start()
    
    time.sleep(2)
    if(monitorOn):
        
        try:
            monitor = MonitorLoader()
            monitor.start()
        except:
            print('error running monitor')
    
    team1 = TeamLoader(team_a.path, team_a.starter)
    team1.start()
    time.sleep(1)
    team2 = TeamLoader(team_b.path , team_b.starter)

    team2.start()
    server.join()



def run_friendly_match(json_file):
    left_teams = []
    right_teams = []
    
    set_server_params(parsed_json)
    
    for s in json_file['Teams'][0].split(','):
        print(s)
        left_teams.append(Team(get_name(s), s.replace(s.split('/')[-1],''), s.split('/')[-1]))
    for s in json_file['Teams'][1].split(','):
        right_teams.append(Team(get_name(s), s.replace(s.split('/')[-1],''), s.split('/')[-1]))
    
    
    #left   = Team(get_name(json_file['Teams'][0]),json_file['Teams'][0].replace(json_file['Teams'][0].split('/')[-1],''), json_file['Teams'][0].split('/')[-1])
    #right  = Team(get_name(json_file['Teams'][1]),json_file['Teams'][1].replace(json_file['Teams'][1].split('/')[-1],''), json_file['Teams'][1].split('/')[-1])


    max_number_match = json_file['match']
    
    for k in range(len(left_teams)):
        left = left_teams[k]
        chdir(path + '/logs')
        system('mkdir '+left.team_name) 
        

        for j in range(len(right_teams)):
            right = right_teams[j]
            chdir(path + '/logs/'+left.team_name)
            system('mkdir '+right.team_name)
            chdir(path + '/logs/'+left.team_name+'/'+right.team_name)
            base = path + '/logs/'+left.team_name+'/'+right.team_name
            round = 1
            
            for i in range(1,max_number_match+1):
                
                print("current,"+ left.team_name +" - "+ right.team_name+'\n')
                conn.sendall( ("current,"+ left.team_name +" - "+ right.team_name+'\n').encode("UTF-8"))
                #####NEED check wether logs directory exist or not
                
                ###########
                chdir(base)

                while isdir('round'+str(round)):
                    round+=1
                system('mkdir round' + str(round)) 
                address = base + '/round' + str(round)
                
                play_game(address,left,right, parsed_json['monitor'])
                for file in listdir(address):
                    if file.endswith(".rcg"):
                        myfile = file.replace('.rcg','')
                left_team_goal,right_team_goal = [int(x) for x in myfile.split(left.team_name+'_')[1].split('-vs-'+right.team_name+'_')]

                parsed_result = {'left':left_team_goal,'right':right_team_goal}
                
                # out = open(address + '/Result.json','r')
                # result_str = out.read()
                # parsed_result = json.loads(result_str)
                
                #print("Parsing The log...")
                #RCGPyParser.parse(address)
                #analyzed = logAnalyzer.analyze(address)
                #print(analyzed)
                ###**************
                #update_result(left, parsed_result["left"],right, parsed_result["right"], round, path)
                
                print(parsed_result["left"])
                print(parsed_result["right"])
        
                left.play(parsed_result["left"],parsed_result["right"])
                right.play(parsed_result["right"],parsed_result["left"])
                #analyzed['left'] = left.__str__()
                #analyzed['right'] = right.__str__()
                #analyzed['game'] =  (str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"             "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8")
                #print(analyzed.__str__().encode("UTF-8"))
                
                #conn.sendall( analyzed.__str__().encode("UTF-8"))
                ###**************
                #print((str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"                 "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8"))
                conn.sendall( (str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"             "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8"))
                
                #s.sendall( (str(round)+'  '+ left.team_name +': '+ str(parsed_result["left"])+'      '+ right.team_name +': '+ str(parsed_result["right"])).encode())
                
                print("left Team  " +str(left))
                print("right Team  " +str(right))
                
                round +=1
                
                print("Itaration" , str(round))
            
                time.sleep(2)
            
    conn.sendall(("END_OF_THE_GAME"+'\n').encode("UTF-8"))
            
    print("Done")

#def run_friendly_match(json_file):
#    
#    
#    set_server_params(parsed_json)
#    
#    left = Team(get_name(json_file['Teams'][0]),json_file['Teams'][0].replace(json_file['Teams'][0].split('/')[-1],''), json_file['Teams'][0].split('/')[-1])
#    right  = Team(get_name(json_file['Teams'][1]),json_file['Teams'][1].replace(json_file['Teams'][1].split('/')[-1],''), json_file['Teams'][1].split('/')[-1])
#
#    max_number_match = json_file['match']
#    round = 1
#    
#    for i in range(1,max_number_match+1):
#        
#        print("current,"+ left.team_name +" - "+ right.team_name+'\n')
#        conn.sendall( ("current,"+ left.team_name +" - "+ right.team_name+'\n').encode("UTF-8"))
#        #####NEED check wether logs directory exist or not
#        
#        ###########
#        chdir(path + '/logs')
#        system('mkdir round' + str(round)) 
#        address = path + '/logs/round' + str(round)
#        
#        play_game(address,left,right, parsed_json['monitor'])
#        
#        out = open(address + '/Result.json','r')
#        result_str = out.read()
#        parsed_result = json.loads(result_str)
#        
#        #print("Parsing The log...")
#        #RCGPyParser.parse(address)
#        #analyzed = logAnalyzer.analyze(address)
#        #print(analyzed)
#        ###**************
#        #update_result(left, parsed_result["left"],right, parsed_result["right"], round, path)
#        
#        print(parsed_result["left"])
#        print(parsed_result["right"])
#
#        left.play(parsed_result["left"],parsed_result["right"])
#        right.play(parsed_result["right"],parsed_result["left"])
#        #analyzed['left'] = left.__str__()
#        #analyzed['right'] = right.__str__()
#        #analyzed['game'] =  (str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"             "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8")
#        #print(analyzed.__str__().encode("UTF-8"))
#        
#        #conn.sendall( analyzed.__str__().encode("UTF-8"))
#        ###**************
#        #print((str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"                 "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8"))
#        conn.sendall( (str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"             "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8"))
#        
#        #s.sendall( (str(round)+'  '+ left.team_name +': '+ str(parsed_result["left"])+'      '+ right.team_name +': '+ str(parsed_result["right"])).encode())
#        
#        print("left Team  " +str(left))
#        print("right Team  " +str(right))
#        
#        round +=1
#        
#        print("Itaration" , str(round))
#    
#    time.sleep(2)
#    
#    conn.sendall(("END_OF_THE_GAME"+'\n').encode("UTF-8"))
#    
#    print("Done")
#    out.close()

    
    
#
#def run_friendly_match(json_file):
#    
#    left = Team(get_name(json_file['Teams'][0]),json_file['Teams'][0].replace(json_file['Teams'][0].split('/')[-1],''), json_file['Teams'][0].split('/')[-1])
#    right  = Team(get_name(json_file['Teams'][1]),json_file['Teams'][1].replace(json_file['Teams'][1].split('/')[-1],''), json_file['Teams'][1].split('/')[-1])
#
#    max_number_match = json_file['match']
#    round = 1
#
#    for i in range(1,max_number_match+1):
#        
#        print("current,"+ left.team_name +" - "+ right.team_name+'\n')
#        conn.sendall( ("current,"+ left.team_name +" - "+ right.team_name+'\n').encode("UTF-8"))
#        
#        chdir(path + '/logs')
#        system('mkdir round' + str(round)) 
#        address = path + '/logs/round' + str(round)
#        
#        play_game(address,left,right, True)
#        
#        out = open(address + '/Result.json','r')
#        result_str = out.read()
#        parsed_result = json.loads(result_str)
#        
#        print("after results"+str(round))
#        
#        ######omit
#        update_result(left, parsed_result["left"],right, parsed_result["right"], round, path)
#        ######
#        
#        print((str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"                 "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8"))
#        conn.sendall( (str(round)+". "+ left.team_name +":  "+ str(parsed_result["left"])+"             "+ right.team_name +":  "+ str(parsed_result["right"])+'\n').encode("UTF-8"))
#        #s.sendall( (str(round)+'  '+ left.team_name +': '+ str(parsed_result["left"])+'      '+ right.team_name +': '+ str(parsed_result["right"])).encode())
#        
#        left.play(parsed_result["left"],parsed_result["right"])
#        right.play(parsed_result["right"],parsed_result["left"])
#        
#        print("left TEAM  " +str(left))
#        print("right TEAM  " +str(right))
#        
#        round +=1
#        print("Itaration" + str(round))
#    
#    time.sleep(2)
#    
#    conn.sendall(("END_OF_THE_GAME"+'\n').encode("UTF-8"))
#    
#    print("Done")
#    out.close()

##read from a file
#config_file = open('gameconfig.json','r')
#json_str = config_file.read()
#parsed_json = json.loads(json_str)
#num_teams = parsed_json['teams']


path = getcwd()

HOST ="127.0.0.85"
PORT = 6969
 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('# Socket created')
 
# Create socket on port
try:
    s.bind((HOST, PORT))
except socket.error as msg:
    print('# Bind failed. ')
    sys.exit()
     
print('# Socket bind complete')
 
# Start listening on socket
s.listen(10)
print('# Socket now listening')



while True:
    
     
    # Wait for client
    conn, addr = s.accept()
    print('# Connected to ' + addr[0] + ':' + str(addr[1]))
     
    # Receive data from client
    data = conn.recv(4096)
    data = data[2:]
    line = data.decode('UTF-8')    # convert to string (Python 3 only)
    line = line.replace("\n","")   # remove newline character
    parsed_json = json.loads(line)
    print( "parsed_json")
    print( parsed_json)
    print( line )    
    
    if parsed_json['mode'] == 'friendly':
        run_friendly_match(parsed_json)
    
    
conn.close()  
s.close()

