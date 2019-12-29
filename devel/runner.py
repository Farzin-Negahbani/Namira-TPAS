
import subprocess
from multiprocessing import Process


def callJava():
    subprocess.run(['java', '-jar', './PSS16-master.jar'])


def callGameRunner():
    subprocess.run(['python', 'gameRunner.py'])


gui = Process(target=callJava)
gui.start()
gameRunner = Process(target=callGameRunner)
gameRunner.start()
