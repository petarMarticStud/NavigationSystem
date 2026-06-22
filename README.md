# Smart Navigation System

A Java-based navigation system that models a road network using a weighted graph and demonstrates graph traversal and pathfinding algorithms.

## Features

### Road Network Management
- Add cities dynamically
- Add road connections with distances
- Display all cities
- Display the complete road network

### Graph Algorithms

#### Depth First Search (DFS)
Checks whether a route between two cities exists.

Example:
```
Start: Wien
Destination: Klagenfurt

Route possible.
```

#### Breadth First Search (BFS)
Searches the graph level by level and shows the visited cities until the destination is reached.

Example:
```
Wien
Linz
Graz
Salzburg
```

#### Dijkstra's Algorithm
Calculates the shortest distance between two cities based on road lengths.

Example:
```
Shortest distance from Wien to Innsbruck: 500 km
```

---

## Data Structure

The road network is implemented as a weighted, undirected graph.

- Vertices = Cities
- Edges = Road connections
- Edge weights = Distances in kilometers

Example:

```
Wien ---- Linz ---- Salzburg ---- Innsbruck
  |
  |
 Graz ---- Klagenfurt
```

---

## Technologies

- Java
- Graph Data Structure
- DFS (Depth First Search)
- BFS (Breadth First Search)
- Dijkstra Algorithm
- Object-Oriented Programming (OOP)

---

## Project Structure

```
src/
│
├── Main.java
├── NavigationSystem.java
├── RoadNetworkGraph.java
├── City.java
└── RoadConnection.java
```

### Classes

#### Main
Application entry point.

#### NavigationSystem
Handles user interaction and visualization of algorithm results.

#### RoadNetworkGraph
Stores the graph structure and implements DFS, BFS and Dijkstra.

#### City
Represents a city (vertex).

#### RoadConnection
Represents a road connection (edge) and its distance.

---

## Example Menu

```
===== SMART NAVIGATION SYSTEM =====

1. Add City
2. Add Road
3. Show All Cities
4. Show Road Network
5. Start DFS
6. Start BFS
7. Start Dijkstra
8. Clear Screen
0. Exit
```

---

## Example Road Network

The application starts with a predefined Austrian road network:

- Wien
- Linz
- Graz
- Salzburg
- Innsbruck
- Klagenfurt

Connections:

- Wien ↔ Linz (180 km)
- Wien ↔ Graz (200 km)
- Linz ↔ Salzburg (130 km)
- Salzburg ↔ Innsbruck (190 km)
- Graz ↔ Klagenfurt (140 km)
- Graz ↔ Salzburg (280 km)

---

## Learning Objectives

This project demonstrates:

- Graph implementation using adjacency lists
- Recursive graph traversal
- Breadth-first traversal
- Shortest-path calculation
- Practical application of graph algorithms
