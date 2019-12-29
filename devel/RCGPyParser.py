from os import walk, path, remove
import sys, os
# folder = input("Please Enter The Folder Name To Open Files From : ")

#folder = sys.argv[1]

#folder = os.getcwd()
folder = ''

write_mode_m = 'w'
write_mode_t = 'w'
write_mode_p = 'w'
write_mode_b = 'w'
write_mode_pm = 'w'
write_mode_sp = 'w'
write_mode_pp = 'w'
write_mode_pt = 'w'



def saveplayer(save_name, playertosave, cycle):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    playerstats = playertosave.split(' ')
    playerstats = [statistic for statistic in [stat.strip() for stat in playerstats] if statistic != '']

    ranged = 34
    have_pointto = False
    have_focus = False

    if playerstats[10] == 'v':
        ranged -= 2
    else:
        have_pointto = True

    if playerstats[ranged - 1 - 13] != 'f':
        ranged -= 3
    else:
        have_focus = True

    if playerstats[0] == 'l':
        team = 'Left'
    else:
        team = 'Right'

    it = 0

    it += 1
    unum = playerstats[it]
    it += 1
    playertype = playerstats[it]
    it += 1
    state = playerstats[it]

    it += 1
    px = playerstats[it]
    it += 1
    py = playerstats[it]
    it += 1
    pvx = playerstats[it]
    it += 1
    pvy = playerstats[it]

    it += 1
    body = playerstats[it]
    it += 1
    neck = playerstats[it]

    pointtox = 'no'
    pointtoy = 'no'

    if have_pointto:
        it += 1
        pointtox = playerstats[it]
        it += 1
        pointtoy = playerstats[it]

    it += 2
    viewquality = playerstats[it]
    it += 1
    viewwidth = playerstats[it]

    it += 2
    stamina = playerstats[it]
    it += 1
    effort = playerstats[it]
    it += 1
    recovery = playerstats[it]
    it += 1
    staminacap = playerstats[it]

    focusside = 'no'
    focusunum = 'no'

    if have_focus:
        it += 2
        if playerstats[it] == 'l':
            focusside = 'Left'
        else:
            focusside = 'Right'
        it += 1
        focusunum = playerstats[it]

    it += 2
    kick = playerstats[it]
    it += 1
    dash = playerstats[it]
    it += 1
    turn = playerstats[it]
    it += 1
    catch = playerstats[it]
    it += 1
    turnneck = playerstats[it]
    it += 1
    changeview = playerstats[it]
    it += 1
    say = playerstats[it]
    it += 1
    tackle = playerstats[it]
    it += 1
    pointtocount = playerstats[it]
    it += 1
    atttocount = playerstats[it]

    player = team + ',' + unum + ',' + playertype + ',' + state + ',' + px + ',' + py + ',' + pvx + ',' + pvy + ',' + body + ',' + neck + ',' + pointtox + ',' + pointtoy + ',' + viewquality + ',' + viewwidth + ',' + stamina + ',' + effort + ',' + recovery + ',' + staminacap + ',' + focusside + ',' + focusunum + ',' + kick + ',' + dash + ',' + turn + ',' + catch + ',' + turnneck + ',' + changeview + ',' + say + ',' + tackle + ',' + pointtocount + ',' + atttocount + '\n'

    msg_file = open(folder + '/' + save_name + '_playerevents.txt', mode=write_mode_p)
    write_mode_p = 'a'
    msg_file.writelines([cycle + ',' + player])
    msg_file.close()


def static_var(varname, value):
    def decorate(func):
        setattr(func, varname, value)
        return func

    return decorate


def readserverparam(save_name, line_to_read):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    #Removeing first element
    line = line_to_read.lstrip('(server_param ')
    #Removing last element (a single closing parentheses)
    line = line.strip()[:-1]
    #Separating each part to be saved
    parts = [part.strip() for part in line.split('(') if part != '']
    
    out=''.strip()
    num = 0
    for part in parts:
        #Writing output data to the output file
        p = str(part)
        p = p.replace(')', '').strip()
        p = p.replace(' ',',').strip()
        k = p.split(',')
        num += 1
        out += k[1].strip()
        out += ','.strip()

    msg_file = open(folder + '/' + save_name + '_serverparam.txt', mode=write_mode_sp)
    out = out.strip()[:-1]
    write_mode_sp = 'a'
    msg_file.writelines(out + '\n')
    msg_file.close()
    
def readplayerparam(save_name, line_to_read):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    #Removeing first element
    line = line_to_read.lstrip('(player_param ')
    #Removing last element (a single closing parentheses)
    line = line.strip()[:-1]
    #Separating each part to be saved
    parts = [part.strip() for part in line.split('(') if part != '']
    out=''.strip()
    num=0
    for part in parts:
        #Writing output data to the output file
        p = str(part)
        p = p.replace(')', '').strip()
        p = p.replace(' ',',').strip()
        k = p.split(',')
        num += 1
        out += k[1].strip()
        out += ','.strip()
        
    msg_file = open(folder + '/' + save_name + '_playerparam.txt', mode=write_mode_pp)
    out = out.strip()[:-1]
    write_mode_pp = 'a'
    msg_file.writelines(out + '\n')
    msg_file.close()

    
def readplayertype(save_name, line_to_read):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    #Removeing first element
    line = line_to_read.lstrip('(player_type ')
    #Removing last element (a single closing parentheses)
    line = line.strip()[:-1]
    #Separating each part to be saved
    parts = [part.strip() for part in line.split('(') if part != '']
    out=''.strip()
    num=0
    for part in parts:
        #Writing output data to the output file
        p = str(part)
        p = p.replace(')', '').strip()
        p = p.replace(' ',',').strip()
        k = p.split(',')
        num += 1
        out += k[1].strip()
        out += ','.strip()

    msg_file = open(folder + '/' + save_name + '_playertype.txt',mode=write_mode_pt)
    out = out.strip()[:-1]
    write_mode_pt='a'
    msg_file.writelines(out + '\n')
    msg_file.close()



@static_var("players", 0)
def readshow(save_name, line_to_read):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    line = line_to_read.lstrip('(show ')
    line = line.strip()[:-1]
    parts = [part.strip() for part in line.split('(') if part != '']

    cycle = parts[0]

    inplayer = False
    currentplayer = ''

    for part in parts:
        passit = False
        if ')' in part:
            part = part.replace(')', '').strip()
            info_part = part.split(' ')

            if 'b' == info_part[0]:
                ballx = info_part[1]
                bally = info_part[2]
                ballvelx = info_part[3]
                ballvely = info_part[4]

                ballevent = ballx + ',' + bally + ',' + ballvelx + ',' + ballvely + '\n'

                msg_file = open(folder + '/' + save_name + '_ballevents.txt', mode=write_mode_b)
                msg_file.writelines([cycle + ',' + ballevent])
                write_mode_b = 'a'
                msg_file.close()

            if 'l' == info_part[0] or 'r' == info_part[0]:
                if inplayer:
                    readshow.players += 1
                    saveplayer(save_name, currentplayer, cycle)
                    currentplayer = ''
                    currentplayer += part
                    passit = True
                else:
                    inplayer = True
                    currentplayer += part
                    passit = True

            if inplayer and not passit:
                currentplayer += ' ' + part

    if currentplayer is not '':
        saveplayer(save_name, currentplayer, cycle)


def readteam(save_name, line_to_read):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    line = str(line_to_read).lstrip('(team ')
    line = str(line).replace(')', '')
    parts = str(line).split(' ')

    cycle = parts[0]
    team_l_name = parts[1].strip()
    team_r_name = parts[2].strip()
    team_l_score = parts[3].strip()
    team_r_score = parts[4].strip()

    team_l_score_pen = '0'.strip()
    team_r_score_pen = '0'.strip()
    team_l_miss_pen = '0'.strip()
    team_r_miss_pen = '0'.strip()

    if len(parts) > 5:
        team_l_score_pen = parts[5].strip()
        team_l_miss_pen = parts[6].strip()
        team_r_score_pen = parts[7].strip()
        team_r_miss_pen = parts[8].strip()

    event = team_l_name + ',' + team_l_score + ',' + team_l_score_pen + ',' + team_l_miss_pen + ',' + team_r_name + ',' + team_r_score + ',' + team_r_score_pen + ',' + team_r_miss_pen + '\n'

    msg_file = open(folder + '/' + save_name + '_teamevents.txt', mode=write_mode_t)
    msg_file.writelines([cycle + ',' + event])
    write_mode_t = 'a'
    msg_file.close()


def readplaymode(save_name, line_to_read):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    line = str(line_to_read).lstrip('(playmode ')
    line = str(line).replace(')', '')
    parts = str(line).split(' ')
    cycle = parts[0]
    playmode = parts[1]

    msg_file = open(folder + '/' + save_name + '_playmodes.txt', mode=write_mode_pm)
    msg_file.writelines([cycle + ',' + playmode])
    write_mode_pm = 'a'
    msg_file.close()


def readmsg(save_name, line_to_read):
    global write_mode_m
    global write_mode_t
    global write_mode_p
    global write_mode_b
    global write_mode_pm
    global write_mode_sp
    global write_mode_pp
    global write_mode_pt
    line = str(line_to_read).lstrip('(msg ')
    line = line[:-2]
    parts = str(line).split(' ')
    cycle = parts[0]
    board = parts[1]
    msg = ''
    for i in range(len(parts) - 2):
        msg += parts[2 + i] + ' '
    msg_file = open(folder + '/' + save_name + '_messages.txt', mode=write_mode_m)
    msg_file.writelines([cycle + ',' + board + ',' + msg + '\n'])
    write_mode_m = 'a'
    msg_file.close()


def readrcg(file_to_read):
    save_name = str(file_to_read).rstrip('.rcg')

    if path.isfile(folder + '/' + save_name):
        remove(folder + '/' + save_name)

    playmodes = 0
    shows = 0
    teams = 0
    msgs = 0

    reading = open(folder + '/' + file_to_read, 'r')
    lines = reading.readlines()

    linenum = 0
    for line in lines:
        linenum += 1

        if line.startswith('(msg'):
            msgs += 1
            readmsg(save_name, line)

        if line.startswith('(show'):
            shows += 1
            readshow(save_name, line)

        if line.startswith('(team'):
            teams += 1
            readteam(save_name, line)

        if line.startswith('(playmode'):
            playmodes += 1
            readplaymode(save_name, line)
	
        if line.startswith('(server_param'):
            readserverparam(save_name, line)
            
        if line.startswith('(player_param'):
            readplayerparam(save_name, line)
            
        if line.startswith('(player_type'):
            readplayertype(save_name, line)

def parse(path):
    global folder
    os.chdir(path)
    folder = path
    files = []
    for (dirpath, dirnames, filenames) in walk(folder):
        files.extend(filenames)
        break
    
    for file in files:
        if str(file).endswith('.rcg'):
            readrcg(file)
