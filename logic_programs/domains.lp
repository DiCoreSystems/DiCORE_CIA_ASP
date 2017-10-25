#script (python)

import clingo

def main(prg):
    prg.ground([("base", [])])
    print ("universe: " + str(len(prg.symbolic_atoms)))
    for x in prg.symbolic_atoms:
        print (x.symbol)
#end. 
