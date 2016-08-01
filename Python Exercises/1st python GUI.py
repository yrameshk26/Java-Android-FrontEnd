from Tkinter import * #Imports GUI Library

# root = Tk() #Defines the root


#######Shows a simple label#######
#
# theLabel= Label(root, text="Hello World!")
# theLabel.pack()
#
#############################################


#######Aligns Button#######
#
# topFrame = Frame(root)
# topFrame.pack()
# bottomFrame = Frame(root)
# bottomFrame.pack(side=BOTTOM)
#
# button1 = Button(topFrame, text="Left", fg="red")
# button2 = Button(topFrame, text="Middle", fg="green")
# button3 = Button(topFrame, text="Right", fg="yellow")
# button4 = Button(bottomFrame, text="Click to Close", fg="white")
#
# button1.pack(side=LEFT)
# button2.pack(side=LEFT)
# button3.pack(side=LEFT)
# button4.pack(side=LEFT)
#
#############################################


#######Fixing positions#######
#
# one = Label(root, text="One", bg="Yellow", fg="Black")
# one.pack()
# two = Label(root, text="Two", bg="Orange", fg="Blue")
# two.pack(fill=X)
# three = Label(root, text="Three", bg="Blue", fg="White")
# three.pack(side=LEFT, fill=Y)
#
#############################################


#######Grid Layout#######
#
# label1= Label(root,text="User Name")
# label2= Label(root,text="Password")
# entry1 = Entry(root)
# entry2 = Entry(root)
#
# label1.grid(row=0, sticky=E)
# label2.grid(row=1, sticky=E)
#
# entry1.grid(row=0,column=1)
# entry2.grid(row=1,column=1)
#
# c=Checkbutton(root, text="Keep Me Logged In")
# c.grid(columnspan=2)
#############################################


#######Linking button to a task -1#######
#
# def printName():
#     print("Checking Text");
#
# button1 = Button(root,text="Print My Name", command=printName)
# button1.pack()
#
#############################################


#######Linking button to a task/event -2#######
#
# def printName(event):
#     print("Checking Text");
#
# button1 = Button(root,text="Print My Name")
# button1.bind("<Button-1>",printName)
# button1.pack()
#
#############################################


#######Mouse Events#######
#
# def leftClick(event):
#     print("left")
#
# def middleClick(event):
#     print("Middle")
#
# def rightClick(event):
#     print("Right")
#
# frame = Frame(root,width=300, height=250)
# frame.bind("<Button-1>",leftClick)
# frame.bind("<Button-2>",middleClick)
# frame.bind("<Button-3>",rightClick)
#
# frame.pack()
#
#############################################

#######Using Classes#######
#
class Ramesh:

    def _init_(self,master):
        frame = Frame(master)
        frame.pack()

        self.printButton = Button(frame, text="Print Message", command=self.printMessage)
        self.printButton.pack(side=LEFT)

        self.quitButton = Button(frame, text="Quit", command=frame.quit)
        self.quitButton.pack(side=LEFT)

    def printMessage(self):
        print("This works")

root = Tk() #Defines the root
b= Ramesh(root)
#
#############################################











root.mainloop()
