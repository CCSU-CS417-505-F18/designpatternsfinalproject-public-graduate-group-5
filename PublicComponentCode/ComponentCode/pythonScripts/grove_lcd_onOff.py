from grove_rgb_lcd import *

def grove_lcd(onOrOff):

  setText("")
  try:
    if(onOrOff == "ON"):
      setRGB(0,128,64)

    if(onOrOff == "OFF"):
        setRGB(0,0,0)

  except IOError:
    return "ERROR"

grove_lcd(sys.argv[1])