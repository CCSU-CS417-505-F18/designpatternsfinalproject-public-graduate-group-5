def hello():
    f = open("demofile.txt", "a")
    f.write("Now the file has one more line!") 
    print("Hello")
def message(message, newMessage):
    f = open("demofile.txt", "a")
    f.write(message + newMessage) 
    print("wrote to file")
	
hello()