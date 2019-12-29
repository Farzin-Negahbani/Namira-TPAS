import math, os, json
from os import walk, path, remove
from numpy import *


p3 = array( [52.5, 30.0] )
p4 = array( [52.5, -30.0] )
p5= array( [-52.5, 30.0] )
p6 = array( [-52.5, -30.0] )

rightTrueShoot = 0
leftTrueShoot = 0
rightMissShoot = 0
leftMissShoot = 0

leftTruePass = 0
rightTruePass = 0
leftIntercept = 0
rightIntercept = 0
leftPassIntercept = 0
rightPassIntercept = 0
onTarget = 0
i = 1
linecnt = 1

TEMP = 0
lastshoot = 0


LastMode = 0

fouls = ["foul_charge_r", "foul_charge_l", "back_pass_r","back_pass_l", "offside_l","offside_r"]

leftShootEnd   = ["goal_kick_r", "free_kick_l","foul_charge_r", "goal_l" ]
rightShootEnd  = ["goal_kick_l","free_kick_r","foul_charge_l","goal_r"]
shootStatus    = 0  

passStatus     = 0   # 0 -> noKick    1 -> akick


lastKickers          = []
kickers              = {}
nextkicker           = ''
playModes            = {}
playerTypesKickArea  = {}
BallEvents           = {} 

def isIN( t, arr):
        for s  in arr:
                if( t in s):
                        return True
        return False

def isInKickArea(agent):
    
        global BallEvents
        global playerTypesKickArea
        global i
        
        ball = BallEvents[i-1]
        dist = math.sqrt( (agent.x - ball.x)**2 + (agent.y - ball.y)**2 )
        
        #print " cycle : " , i, "  dist", dist, " Ball x , y :" , ball.x, ball.y  , " KickArea :  " , float(playerTypesKickArea[agent.type]), "   " ,  dist < float(playerTypesKickArea[agent.type])
        s = (playerTypesKickArea[agent.type])
        if ( dist < float(s)+ 0.85):
                return True
        
        return False


def getLastMode(i):
    
        global playModes
        b = 1

        for x in playModes:
                if ( i < x ):
                        return b
                b = x


def getKicker(i):
        
        global kickers
        global lastKickers

        if(len(lastKickers)> 0):
                kickers[lastKickers[-1].cycle] = lastKickers[-1]
                lastKickers  = []
       
def Cycle():
        
        global teamL, teamR, kickers, passStatus, i, lastshoot
        global BallEvents
        global leftTruePass 
        global rightTruePass 
        global leftIntercept 
        global rightIntercept 
        
        global shootStatus
        global rightShootEnd
        global leftShootEnd

        global TEMP
        global LastMode

        global rightTrueShoot 
        global leftTrueShoot 
        global rightMissShoot 
        global leftMissShoot

        global fouls
        global onTarget

        global rightPassIntercept
        global leftPassIntercept
        
        global playModes

        if( i != 2999 and i != 3000 and i != 5999 and i!= 6000 and i != 3001  ):
                ball = BallEvents[i]
                ball2 = BallEvents[i+1]

                #ball = BallEvents[i-1]
                #ball2 = BallEvents[i]
            
                sortedKeys = sorted(kickers.keys())
                #sortedKeys.sort()

                if(i in playModes):
                        LastMode = playModes[i]

                if ( shootStatus == 0 and i in kickers):
                        
                        if ( kickers[i].team == "Right"):
                                dist = math.sqrt((kickers[i].x + 52.5)**2 + (kickers[i].y)**2)
                                team = "Right"
                        elif( kickers[i].team == "Left"):
                                dist = math.sqrt((kickers[i].x - 52.5)**2 + (kickers[i].y)**2)
                                team =  "Left"
                        else:
                                team = ""
                                
                        #print  " CYCLE : ", i , " dist", dist, " team : " ,kickers[i].team, "number " , kickers[i].number, " intersection: " ,  abs(conflict(Line2D(ball.x,ball.y,ball2.x,ball2.y),team))
                        
                        if( dist < 25 and abs(conflict(Line2D(ball.x,ball.y,ball2.x,ball2.y),team))<12  and (abs(ball2.vx)>1.8 or abs(ball2.vy)>1.8) and lastshoot != i-1):
                                
                                if(abs(conflict(Line2D(ball.x,ball.y,ball2.x,ball2.y),team)) < 7.01 ):
                                        onTarget = 1
                        
                                else:
                                        onTarget =  0
                                
                                
                                if( kickers[i].team == "Right" and ball2.x < ball.x):
                                        #shootStatus = 1
                                        lastshoot = i
                                        TEMP = kickers[i]
                                        if(onTarget):
                                                rightTrueShoot +=1
                                                print (" *******Cycle:", i, " On Target shoot from Right  number ", TEMP.number)
                                        else:
                                                rightMissShoot +=1
                                                print (" *******Cycle:", i, " Off Target shoot from Right  number ", TEMP.number)

                                elif( kickers[i].team == "Left" and ball2.x > ball.x):
                                        #shootStatus = 1
                                        TEMP = kickers[i]
                                        lastshoot = i
                                        if(onTarget):
                                                leftTrueShoot +=1
                                                print (" *******Cycle:", i, " On Target shoot from left  number ", TEMP.number)
                                        else:
                                                leftMissShoot +=1
                                                print (" *******Cycle:", i, " Off Target shoot from left  number ", TEMP.number)

                 
                
                # elif (i in kickers and (kickers[i].team !=kickers[sortedKeys[-2]].team )  and shootStatus == 1):
                #         if( kickers[sortedKeys[-2]] == "Right" and onTarget):
                #                 #print " *******Cycle:", i, " shoot from Right  number ", kickers[sortedKeys[-2]].number
                #                 print (" *******Cycle:", i, " On Target shoot from Right  number ", TEMP.number)
                #                 shootStatus     = 0
                #                 rightMissShoot += 1
                #         elif(kickers[sortedKeys[-2]] == "Left" and onTarget) :
                #                 #print " *******Cycle:", i, " shoot from Left  number ", kickers[sortedKeys[-2]].number
                #                 print (" *******Cycle:", i, " on Targetshoot from Left  number ", TEMP.number)
                #                 shootStatus    = 0
                #                 leftMissShoot += 1
                        
                # elif(i in playModes and "play_on" not in playModes[i] and shootStatus == 1):
                        
                #         if(kickers[sortedKeys[-1]].team == "Right"  and isIN(playModes[i],rightShootEnd) ):
                               
                #                if(onTarget):
                #                         rightTrueShoot +=1
                #                         print (" ##Cycle:", kickers[sortedKeys[-2]].cycle, "On Target  shoot from Right  number ", kickers[sortedKeys[-1]].number)
                #                else:
                #                         rightMissShoot +=1
                #                         print (" ##Cycle:", kickers[sortedKeys[-2]].cycle, " shoot from Right  number ", kickers[sortedKeys[-1]].number)
                                        
                #                 #print " ##Cycle:", TEMP.cycle, " shoot from Right  number ", TEMP.number
                        # elif(kickers[sortedKeys[-1]].team == "Left" and playModes[i] in leftShootEnd):
                        #         if(onTarget):
                        #                 leftTrueShoot +=1
                        #                 print ("##Cycle:", kickers[sortedKeys[-2]].cycle, " On Target shoot from Left  number ", kickers[sortedKeys[-1]].number)
                        #         else:
                        #                  leftMissShoot +=1
                        #                  print ("##Cycle:", kickers[sortedKeys[-2]].cycle, " shoot from Left  number ", kickers[sortedKeys[-1]].number)
                                        
                                        
                                
                        #         #print "##Cycle:", TEMP.cycle, " shoot from Left  number ", kickers[sortedKeys[-1]].number
                        #         #print "##Cycle:", TEMP.cycle, " shoot from Left  number ", TEMP.number

                        # shootStatus = 0
                        
                
        if ( passStatus == 0):
               # if(i in kickersand "play_on" in playModes[getLastMode(i)]):
               if(i in kickers and LastMode == "play_on"):
                        passStatus = 1
               if( ("goal_kick_r" in LastMode) or ("goal_kick_l" in LastMode)  and i in kickers ):
                        passStatus = 1
                        
##              if(i in playModes and playModes[i] == "play_on"):
##                        passStatus = 1
        
        elif ( passStatus  == 1):
                
                #if(i in playModes):
                        #print "cycle : " , i, " playModes : ", playModes[i] 
                if(i in playModes and playModes[i] !="play_on"):
                        #print "cycle : " , i
                        passStatus = 0
                        
                                
                
                elif(i in kickers):

                        sortedKeys = sorted(kickers.keys())
                        #sortedKeys.sort()
                        
                        if ( kickers[sortedKeys[-2]]!= kickers[i] and kickers[sortedKeys[-2]].team == kickers[i].team) :
                               # print (kickers[i].team, "Team successful pass at cycle:", i, " between ", kickers[sortedKeys[-2]].number , " and ", kickers[i].number)
                                if( kickers[i].team == "Right"):
                                        rightTruePass +=1
                                else:
                                        leftTruePass  +=1
                                        
                        elif (kickers[sortedKeys [-2]].team != kickers[i].team ):
                                
                                if( kickers[i].team == "Right" ):
                                        if( abs(BallEvents[sortedKeys [-2]].vx) > 1.6  or abs(BallEvents[sortedKeys [-2]].vy )> 1.6):
                                                rightPassIntercept +=1
                                                #print ("Pass interception from ",kickers[i].team," number: ",kickers[i].number , " Cycle: ", i)
                                        else:
                                                rightIntercept +=1
                                                #print ("interception from ",kickers[i].team," number: ",kickers[i].number , " Cycle: ", i)
                                else:
                                        if( abs(BallEvents[sortedKeys [-2]].vx) > 1.6  or abs(BallEvents[sortedKeys [-2]].vy )> 1.6):
                                                leftPassIntercept +=1
                                                #print ("Pass interception from ",kickers[i].team," number: ",kickers[i].number , " Cycle: ", i)
                                        else:
                                                leftIntercept +=1
                                                #print ("interception from ",kickers[i].team," number: ",kickers[i].number , " Cycle: ", i)
                                        

                                #passStatus = 0
                                
                                
                        #passStatus = 0
                        
                        if(kickers[sortedKeys[-2]] == kickers[sortedKeys[-1]]):
                                passStatus = 1

##                        if(len(sortedKeys) > 4):
##                                kickers.pop(sortedKeys[-4])


class Ball():
        
        def __init__ (self, temp):
                z = temp.split(",")
                self.x  = float(z[1])
                self.y  = float(z[2])
                self.vx = float(z[3])
                self.vy = float(z[4])

class Team():

        leftKicker   = 0
        rightkicker = 0
        
        def __init__(self,team):
                self.team    = team
                self.Agents = []
                for i in range(1,12):
                        self.Agents.append(Agent(i))

        
        def fetchData(self,line):
                # res template
                #  cycle  team  unum  playertype  state  5=px  6=py  pvx  pvy  body  neck  pointtox
                # pointtoy  viewquality  viewwidth  stamina  effort  recovery  staminacap  focusside  focusunum
                #  21=kick  dash  turn  catch  turnneck  changeview  say  28=tackle  pointtocount  atttocount 
##                res = line.split(',')
##                try:
##                        self.Agents[int(res[2])-1].setter(res)
##                except:
##                        print  "Err  : ", int(res[2])
##
                res = line.split(',')
                self.Agents[int(res[2])-1].setter(res)
         
                        
class Line2D:
        #x = 0
        #y = 0
        #xBoard = 0
        #yBoard = 0
        def __init__(self,x,y,x2,y2):
                self.x = x
                self.y = y
                self.x2 = x2
                self.y2 = y2


def conflict(line , team ):
        global p3
        global p4
        global p5
        global p6
        
        p1 = array( [line.x,line.y] )
        p2 = array( [line.x2, line.y2] )

        if(team == "Left"):
                if (line.x2-line.x == 0):
                        return 100

                y  = seg_intersect( p1,p2, p3,p4)[1]
               #y  = (line.y2 - line.y/line.x2-line.x)* (-52.5 - line.x2) +line.y2
                
                return abs(y)
        
        elif(team == "Right"):
                if (line.x2-line.x == 0):
                        return 100
               # y  =  (-line.yBoard/line.xBoard)*(52.5 - line.x)+ line.y
                #y  = (line.y2 - line.y/line.x2-line.x)* (52.5 - line.x2) +line.y2
                y  = seg_intersect( p1,p2, p5,p6)[1]
                return abs(y)
     
   


### with velovity Vector                 
##def conflict(line , team ):
##        if(team == "Right"):
##                if (line.xBoard == 0):
##                        return 100
##                y  =  (-line.yBoard/line.xBoard)*(-52.5 - line.x)+ line.y
##                return abs(y)
##        
##        elif(team == "Left"):
##                if (line.xBoard == 0):
##                        return 100
##                y  =  (-line.yBoard/line.xBoard)*(52.5 - line.x)+ line.y
##                return abs(y)
##        else:
##                print "RIIDIII"
##      

##def conflict(line1):
##        if line1.xBoard <= 0:
##                return 100
##        x = (52.5-line1.x)/line1.xBoard
##        y = (x-line1.x)/line1.xBoard*line1.yBoard + line1.y
##        return y



class Agent():

        def __init__(self, number):
                self.number    = number
                self.isKicked  = 0
                self.isTackled = 0
                self.team      = ''
                self.type      = 0
                #self.lastKickCycle  = 0
                self.kickNumber    = 0
                self.tackleNumber = 0
                self.x  = 0
                self.y  = 0
                
        def setter(self, res):
                global lastKickers
                
                self.isKicked  = 0
                self.isTackled = 0
                self.x        = float(res[5])
                self.y        = float(res[6])
                self.cycle  = int(res[0])
                self.team  = res[1]
                self.type   = int(res[3])

                        
                if(self.kickNumber < int(res[21]) ):
                        
                        #print "Kicked at cycle:", res[0], "  Team :", res[1], "  number :",res[2]
                        if(isInKickArea(self)):
                                self.isKicked  = 1
                                lastKickers.append(self)
                                
                        self.kickNumber =  int(res[21])
                        
                        
                if(self.tackleNumber < int(res[29]) ):
                        #print "Tackled at cycle:", res[0], "Team : ", res[1], "number : ",res[2]
##                        self.isTackled  = 1
##                        if(self.isKicked == 0):
##                                  lastKickers.append(self)
##                        self.tackleNumber = int(res[29])
                        self.tackleNumber =  int(res[29])

                        if(isInKickArea(self)):
                                self.isTackled  = 1
                                lastKickers.append(self)
                                if(self.isKicked == 0):
                                        self.isKicked  = 1
                
        def show(self):
                print  ("number: " ,self.number)


#############
def seg_intersect(a1,a2, b1,b2) :
    da = a2-a1
    db = b2-b1
    dp = a1-b1
    dap = perp(da)
    denom = dot( dap, db)
    num = dot( dap, dp )
    return (num / denom.astype(float))*db + b1

def perp( a ) :
    b = empty_like(a)
    b[0] = -a[1]
    b[1] = a[0]
    return b

def analyze(path, log= True):
    os.chdir(path)
    global lastKickers          
    global kickers              
    global nextkicker           
    global playModes            
    global playerTypesKickArea  
    global BallEvents      
    global i
    global linecnt 
    #tackles = 0
    
    
    print("Analyzing The Game ...")
    
    files = []
    for (dirpath, dirnames, filenames) in walk(path):
        files.extend(filenames)
        break
    
    for file in files:
        if str(file).endswith('playmodes.txt'):
            playModesFile  = open(file,'r')
        if str(file).endswith('playerevents.txt'):
            playerEvents  = open(file,'r')
        if str(file).endswith('playertype.txt'):
            playerTypesFile  = open(file,'r')
        if str(file).endswith('ballevents.txt'):
            BallEventsFile  = open(file,'r')
    
    for line in playModesFile:
            playModes[int(line.split(',')[0])] = line.split(',')[1].strip('\n')
    
    for line in playerTypesFile:
            playerTypesKickArea[int(line.split(',')[0])] = line.split(',')[7].strip('\n')
            #print int(line.split(',')[0]),"   ", line.split(',')[7], "  " , type(playerTypesKickArea[int(line.split(',')[0])])
    
    for line in BallEventsFile:
            BallEvents[int(line.split(',')[0])] = Ball(line)
   
    
    teamR = Team("Right")
    teamL = Team("Left")
    
    #############
    
    
    for line in  playerEvents:
            
            if( i == 3000):
                    i = 3001        
                    
             
            if (  (int(line.split(',')[0]) == i ) and (line.split(',')[1] == 'Right'  ) ):
                    linecnt +=1
                    teamR.fetchData (line)
                    #print " ACC R: ", line, " Cycle:",i
                    if(linecnt > 11):
                            linecnt = 1
                            getKicker(i)
                            Cycle()
                            i+=1
    
                                           
            if (int(line.split(',')[0]) == i and  'Left' in (line.split(','))[1]  ):
                    linecnt +=1
                    teamL.fetchData (line)
                    #print " ACC L: ", line , " Cycle:",i
                    if(linecnt > 11):
                            linecnt = 1
                            

    data = {}
    #data['key'] = 'value'
    data['R_True_Pass'] = rightTruePass
    data['R_intercept'] = rightIntercept
    data['R_True_Shoot'] = rightTrueShoot
    data['R_Miss_Shoot'] = rightMissShoot
    data['R_Pass_Intercept'] = rightPassIntercept
    
    data['L_True_Pass'] = leftTruePass
    data['L_intercept'] = leftIntercept
    data['L_True_Shoot'] = leftTrueShoot
    data['L_Miss_Shoot'] = leftMissShoot
    data['L_Pass_Intercept'] = leftPassIntercept
    
    if(log):
        print(data)
    else:
        return data
    #return json.dumps(data)  
#                  
#    print ("Right True Pass : " ,rightTruePass)
#    print ("Right intercept : " ,rightIntercept)
#    print ("Right True Shoot : " ,rightTrueShoot)
#    print ("Right miss Shoot : " ,rightMissShoot)
#    print ("Right Pass Intercept: " , rightPassIntercept)
#    
#    print ("left True Pass : " ,leftTruePass)
#    print ("left intercept: " ,leftIntercept)
#    print ("left True Shoot : " ,leftTrueShoot)
#    print ("left miss Shoot: " ,leftMissShoot)
#    print ("left Pass Intercept: " , leftPassIntercept)
    
    
#analyze('/home/farzin/Farzin/Robocup/NamiraSoftware/Version1.4/PSS16-master/PSS16-master/logs/round1')