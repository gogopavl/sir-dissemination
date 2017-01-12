'''SIR diffusion process in an aggregated network. The process begins with a set of infected nodes, rather than a single one. 
Returns the size of the outbreak, i.e. the number of infected and recovered nodes. 
If all infected nodes recover before the process ends, then only the number of recovered is returned.'''

from __future__ import division
# -*- coding: utf-8 -*-


"""Limitations: 1) The infected state has a constant duration --> defined by gamma --> 
   1/γ: average infectious period
   2) Several contacts involving a node during the same time step --> sample the contacts
      during a time step in a random order."""

      
import random
import time
import math
import networkx as nx
from copy import copy

start_time = time.time()


def network(filename):    
    edges = []
    with open(filename) as f: 
        for line in f:
            u = line.split()[0]
            v = line.split()[1]
            edges.append((u,v))
    G = nx.Graph()
    G.add_edges_from(edges)
    
    return G

    
def sir(G,beta,gamma,source):
    state = {}
    inf_time = {}
    infectious = []
    to_be_infected = []
    recovered = []
    
    #Initialization
    for node in G.nodes():
        state[node] = 'S'
    for infect_node in source:
        state[infect_node] = 'I'
    infectious = copy(source)
    for i in infectious:
        inf_time[i] = 0
    t = 0
    
    while infectious: #Until the outbreak dies, i.e. all infected nodes have recovered
        for i in infectious:
            if t - inf_time[i] > gamma:
                infectious.remove(i)
                state[i] = 'R'
                recovered.append(i)
            
        #Go through the SI contacts
        del to_be_infected[:]
        for node in infectious:
            for neigh in G.neighbors(node):
                if state[neigh] == 'S':
                    rand = random.random()
                    if rand <= beta and neigh not in to_be_infected: #At the same run, a node may be infected by more than one infectious neighbors. Actually, he is infected by the first neighbor he contacts to. 
                        to_be_infected.append(neigh)
        
        t += 1
              
        #Change the ones to be infectious at next time step
        for i in to_be_infected:
            state[i] = 'I'
            infectious.append(i)
            inf_time[i] = t        
    
    return recovered


def avgSize(G,seedSet,beta,gamma,iterations):
    avg = 0
    for i in range(iterations):
        avg += float(len(sir(G,beta,gamma,seedSet)))/iterations
    
    return avg
    

beta = 0.01 #infection probability
gamma = 1 #recovery rate
runs = 10000 #number of runs of the stochastic process ~ for accurate results take 10000-20000
source = []
dSIR = {}
filename = 'file.txt' #file containing the network where user A in first column communicates with user B in second column
seedsFile = 'seeds.txt' #file containing a column with the top ranked influential users, as extracted from a certain method
G = network(filename)

with open(seedsFile) as f:
    for line in f:
        user = line.split()[0]
        source.append(user.lower())        

"""Check the diffusion ability for different numbers of initial spreaders. In this case, the seed set's size is taken as 10,20,30,40,50"""         
for s in range(10,51,10):
    dSIR[s] = math.ceil(avgSize(G,source[:s],beta,gamma,runs))

print dSIR


"""Calculate execution time"""
seconds = time.time() - start_time
m,s = divmod(seconds,60)
h,m = divmod(m,60)
print(h,m,s)