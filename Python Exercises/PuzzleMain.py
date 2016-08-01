from random import randint

def puzzlemake ():
    count=0
    for i in range (0,height):
        for j in range (0,width):
            Squares[i][j]=count
            count+=1
            
    for i in range (0,500):
        move(randint(1,((width*height)-1)))
    
def display():
    print("")
    for i in range(0,height):
        for j in range (0,width):
            print(formatDisplay(Squares[i][j])),
        print("")
    
def formatDisplay(number):
    if number== 0:
        return "  "
    elif number<10:
        return " " + str(number)
    else:
        return number;
    
def move(number):    
    if number >= (width * height):
        return
    for i in range(0,height):
        for j in range(0,width):
            if(Squares[i][j]==number):
                if (tryAbove(i,j)): 
                    return
                if (tryBelow(i,j)): 
                    return
                if (tryLeft(i,j)): 
                    return
                if (tryRight(i,j)): 
                    return
    
def tryAbove(i,j):
    if i==0:
        return False
    if (Squares[i-1][j] != 0 and tryAbove(i-1,j)== False):
        return False
    swap(i, i-1, j, j)
    return True
    
def tryBelow(i,j):
    if i==height-1:
        return False
    if (Squares[i+1][j] != 0 and tryBelow(i+1,j)== False):
        return False
    swap(i, i+1, j, j)
    return True

def tryLeft(i,j):
    if j==0:
        return False
    if (Squares[i][j-1] != 0 and tryLeft(i,j-1)== False):
        return False
    swap(i, i, j, j-1)
    return True

def tryRight(i,j):
    if j==width-1:
        return False
    if (Squares[i][j+1] != 0 and tryRight(i,j+1)== False):
        return False
    swap(i, i, j, j+1)
    return True 

def swap(i1,i2,j1,j2):
    temp = Squares[i1][j1]
    Squares[i1][j1] = Squares[i2][j2]
    Squares[i2][j2] = temp

def inputMoveCheck():
    try:
        moveNum = input("\nMove:")
        if moveNum >=0:
            return moveNum 
        else:
            print("Enter a valid number:")
            return inputMoveCheck()
    except (RuntimeError, TypeError, NameError):
            print("Enter a valid number:")
            return inputMoveCheck()
        
def inputWidthHeightCheck():
    try:
        num = input()
        if num >0:
            return num 
        else:
            print("Enter a valid number:")
            return inputWidthHeightCheck()
    except (RuntimeError, TypeError, NameError):
            print("Enter a valid number:")
            return inputWidthHeightCheck()

print("Enter Height:"),
height = inputWidthHeightCheck()
print("Enter Width:"),
width = inputWidthHeightCheck()
Squares = [[0 for x in range(width)] for y in range(height)]
puzzlemake()
while (True):
    display()
    inputNum = inputMoveCheck()
    if inputNum==0:
        break
    move(inputNum)



