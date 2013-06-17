#Import Tkinter module 
from Tkinter import *


#This class contains all elements in the gui and manages the interactions with
#the user.
class UgvGui:


#Variables are stored in class attributes
    speed = 0
    rotation = 90

    #Initiate grafical interface
    def __init__(self, master):

        self.master = master
        
        #Make and place buttons, labels and text zones
        self.speed_label = Label(master, text="Speed %i " % (self.speed))
        self.speed_label.grid(column= 1, sticky= S)


        self.rotation_label = Label(master, text="Rotation %i "
                                    % (self.rotation))
        self.rotation_label.grid(column= 1)


        self.exit = Button(master, text= "QUIT", fg="red", command=master.quit)
        self.exit.grid(column= 1, sticky= SE)


        self.messages = Text(master, height= 20, width= 50, 
                             background= "white", border= 2)
        self.messages.grid(column= 0, row= 0, rowspan= 3)

        #Bind key events to class functions
        master.bind("<Escape>", self.escape)
        master.bind("<Shift-Up>", self.shift_up)
        master.bind("<Shift-Down>", self.shift_down)
        master.bind("<Shift-Right>", self.shift_right)
        master.bind("<Shift-Left>", self.shift_left)


    #Define on key-stroke-event functions
    def escape(self, event):
        print "Quit: UgvGui"
        #Quits the application
        self.master.destroy()

    def shift_up(self, event):
        if self.speed < 200:
            #set new speed if in range
            self.speed = self.speed + 20
            self.speed_label.config(text = "Speed %i" % (self.speed))
            print "SPEED: new speed is %i" % (self.speed)
        else:
            #if the the new speed should be out of range warn the user
            print "SPEED: warning! max speed is reached"
            self.messages.insert(END, "SPEED: warning! max speed is reached \n")

    def shift_down(self, event):
        if self.speed > 0:
            self.speed = self.speed - 20
            self.speed_label.config(text = "Speed %i" % (self.speed))
            print "SPEED: new speed is %i" % (self.speed)
        else:
            print "SPEED: warning! minimal speed is reached"
            self.messages.insert(END, "SPEED: warning! minimal speed is reached \n") 
        
    def shift_right(self, event):
        if self.rotation < 140:
            self.rotation = self.rotation + 10
            self.rotation_label.config(text = "Rotation %i" % (self.rotation))
            print "ROTATION: new angle is %i" % (self.rotation)
        else:
            print "ROTATION: warning! max angle is reached"
            self.messages.insert(INSERT, "ROTATION: warning! max angle is reached \n") 
    
    def shift_left(self, event):
        if self.rotation > 50:
            self.rotation = self.rotation - 10
            self.rotation_label.config(text = "Rotation %i" % (self.rotation))
            print "ROTATION: new angle is %i" % (self.rotation)
        else:
            print "ROTATION: warning! minimal angle is reached"
            self.messages.insert(INSERT, "ROTATION: warning! minimal angle is reached \n") 

#Make root Tkinter object
root = Tk()

#Update the message box to always show the last message
def update_messages():
    ugv.messages.see(END)
    root.after(1, update_messages)
    
#launch gui
ugv = UgvGui(root)

#run update_message function every 100ms
root.after(100, update_messages)

#initiate gui mainloop
root.mainloop()
