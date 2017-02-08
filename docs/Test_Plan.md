## CS1632 Project 2: Requirement and Test case

### Requirement
1. Functional

  * FUNC-1 Two Players game: This is a game between 2 players, one player cannot play alone, no third player can involve.

  * FUNC-2 Board size : Board size is 10x10 ( horizontal is A-J, vertical is 1-10)

  * FUNC-3 Ship placement

    * FUNC-3.1 Ship placement input format: Must be valid characters (A-J, 1-10), in correct format: [A-J]:[1-10]; both upper and lowecase are allowed. For example A:3, d:7.

    * FUNC-3.2 Ship number: Each player has 5 ships, 1 in each type: carrier, battleship, cruiser, submarine, destroyer.
	
	* FUNC-3.3 Ships size: Carrier: 5 spaces, battleship: 4 spaces, cruiser: 3 spaces, submarine: 3 spaces, destroyer: 2 spaces.

    * FUNC-3.4 Ship placed in horizontal or vertical only: no diagnal ship placement is allowed

    * FUNC-3.5 Ships overlap: No parts/ spaces in any ships can overlap.

    * FUNC-3.6 Ships out of bound: Ships must be placed inside board.
  
  * FUNC-4 Player's input during game
  
	* FUNC-4.1 Player's shot must be valid: characters (A-J, 1-10), in correct format: [A-J]:[1-10]; both upper and lowecase are allowed. For example A:3, d:7.

    * FUNC-4.2 Player's turn: Player can only shot in his turn. Any input in oponent's turn must be ignored.

    * FUNC-4.3 Valid shot coordination: Player's shot coordination must be inside the board.	

    * FUNC-4.4 Duplicate shot: Player can shoot a position 1 time only. Duplicate shot not allowed.
	
	* FUNC-4.5 Player quit/surrender in the middle: Player can surrender/ quit in the middle of the game. Type 'q' to quit; 's' to surrender.

  * FUNC-5 Game play	
  
    * FUNC-5.1 Ships placement must be finished before shooting phase.  
    
	* FUNC-5.2 Ship sunk announcement: If all spaces in one ship are shotdown, 2 players must be announced that ship has been destroyed. For example: Player 1 carrier has been destroyed.
    
    * FUNC-5.3 Ship hit/miss announcement: If a shot hit a ship, both players must be announced that shot was a hit, otherwise they should be announced it was a miss.
    
    * FUNC-5.4 Winner decision: If a player's all 5 ships has been destroyed, the game should announce a winner and finish.

    * FUNC-5.6 Time limit: Player has limited time to make decision. Place the ship: 2 min maximum. Each turn: 30 second. For any reasons (afk, game client crash, internet lost) a player cannot finish his move, he's considered to lose the match.

    * FUNC-5.7 No ships movement after shooting phase start.
	
	* FUNC-5.8 Oponent ship disclosure: Player cannot see oponent's ship placement.

2. Non Functional

  * NONFUNC-1 Game client must run on all 3 operating system Linux/Windows/Mac.

  * NONFUNC-2 Game server must run on Java language:

  * NONFUNC-2 Players can connect to server and play through network.

  
3. Optional
  * Random start
  * Multiple game simultaneously
  
  
### Test Case
Test cases are from @Team6 @Team 1 and @Team10. Good job!!!
For simple traceability, test cases are named with same requirement function number. For example test case TC-1a and TC-1b belongs to FUNC-1; TC-Non1a belongs to NONFUNC-1
To find more bugs, for each requirement you can have your own new test cases. Don't need to rely totally on these test cases belows.

------------------------------------------------------------------------------
# TC-1

TC-1a: TEST-PLAYER_1_CONNECT
4.4.1. Test Case: This is a test to see what happens when the first player attempts to join
a game server.
4.4.2. Preconditions: There are no players currently in the specified game server.
4.4.3. Input Values: Player 1 will connect to the game server.
4.4.4. Execution Steps: Player 1 should launch the client program and attempt to join a
game server that is currently empty.
4.4.5. Post Conditions: Player 1 should be allowed to join the game server and should be
placed into a game. The game server should have one player after this test. The
game should not start and should instead wait for a second player.

TC-1b: TEST-PLAYER_2_CONNECT
4.5.1. Test Case: This is a test to see what happens when a second player attempts to
join a game server that is already occupied by one other player.
4.5.2. Preconditions: There is one player currently in the specified game server.
4.5.3. Input Values: Player 2 will connect to the game server.
4.5.4. Execution Steps: Player 2 should launch the client program and attempt to join a
game server that is already occupied by one other player.
4.5.5. Post Conditions: Player 2 should be allowed to join the game server and should be
placed into a game. The game server should have two players after this test.

------------------------------------------------------------------------------
# TC-2
TC-2a
------------------------------------------------------------------------------
# TC-3.1

------------------------------------------------------------------------------
# TC-3.2


TC-3.2a: The server will prompt the user for each of the ships
placements separately and sequentially.
ii) Preconditions: The server must have randomly matched two players.
iii) Input Values: All five ships’ placements.
iv) Execution Steps: The game will prompt the user for the first ship, then will
prompt the user for the second ship after the user has entered the
placement for the first and it does not violate any rules, then the third,
then fourth, then the fifth.
v) Output Values: Expected output would be all ships are successfully
placed without any placement violations.

------------------------------------------------------------------------------
# TC-3.3

TC-3.3a: Correct Ship Input Test X Direction
i. Test Case: All ships must accept the given input values and be placed
properly on the board
ii. Preconditions:
1. It is the beginning of a player’s ship placement period
iii. Input Values:
1. A1-A5 for Carrier
2. B1-B4 for Battleship
3. C1-C3 for Destroyer
4. D1-D3 for Submarine
5. E1-E2 for Patrol Boat
iv. Execution Steps:
1. Input the given values for the Carrier. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Carrier entry. End the test and test
again later.
2. Input the given values for the Battleship. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Battleship entry. End the test and
test again later.
3. Input the given values for the Destroyer. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Destroyer entry. End the test and
test again later.
4. Input the given values for the Submarine. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Submarine entry. End the test and
test again later.
5. Input the given values for the Patrol ship. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Patrol ship entry. End the test and
test again later.
v. Output Values:
1. All ships should be placed correctly given the input values
2. On invalid shot, an error message stating which coordinates are
invalid.
vi. Postconditions: Other Player should be given their turn to place their
ships

TC-3.3b: Correct Ship Input Test Y Direction
i. Test Case: All ships must accept the given input values and be placed
properly on the board
ii. Preconditions:
1. It is the beginning of a player’s ship placement period
iii. Input Values:
1. A1-E1 for Carrier
2. A2-D2 for Battleship
3. A3-C3 for Destroyer
4. A4-C4 for Submarine
5. A5-B5 for Patrol Boat
iv. Execution Steps:
1. Input the given values for the Carrier. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Carrier entry. End the test and test
again later.
2. Input the given values for the Battleship. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Battleship entry. End the test and
test again later.
3. Input the given values for the Destroyer. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Destroyer entry. End the test and
test again later.
4. Input the given values for the Submarine. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Submarine entry. End the test and
test again later.
5. Input the given values for the Patrol ship. Check that the ship was
placed correctly
a. If the ship is rejected, stop the test and mark that the
failure came from the Patrol ship entry. End the test and
test again later.
v. Output Values:
1. All ships should be placed correctly given the input values
2. On invalid shot, an error message stating which coordinates are
invalid.
vi. Postconditions: Other player should be given their turn to place ships/fire
on ships

TC-3.3c: Too Short Ship Input Test X Direction
i. Test Case: All ships must accept the given input values and be placed
properly on the board
ii. Preconditions:
1. It is the beginning of a player’s ship placement period
iii. Input Values:
1. A1-A4 for Carrier (if/when the coordinates fail which is ideal, input
A1:A5)
2. B1-B3 for Battleship (input B1:B4 on fail)
3. C1-C3 for Destroyer (input C1:C3 on fail)
4. D1-D2 for Submarine (input D1:D3 on fail)
5. E1-E1 for Patrol Boat (input E1:E2 on fail)
iv. Execution Steps:
1. Input the given values for the Carrier. Check that the ship’s
coordinates were rejected
a. If the ship is accepted, stop the test and mark that the
failure came from the Carrier entry. End the test and test
again later.
b. If the ship is rejected, input valid coordinates for the Carrier
to continue
2. Input the given values for the Battleship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Battleship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Battleship to continue
3. Input the given values for the Destroyer. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Destroyer entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Destroyer to continue
4. Input the given values for the Submarine. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Submarine entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Submarine to continue
5. Input the given values for the Patrol ship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Patrol ship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the Patrol
boat to continue
v. Output Values:
1. All ships should have rejected the initial, purposely incorrect
values listed above
2. On invalid shot, an error message stating which coordinates are
invalid.
vi. Postconditions: All ships should have been placed correctly after inputting
the CORRECT VALUES provided above
ii. TC-29: Too Short Ship Input Test Y Direction
i. Test Case: All ships must accept the given input values and be placed
properly on the board
ii. Preconditions:
1. It is the beginning of a player’s ship placement period
iii. Input Values:
1. A1-D1 for Carrier (if/when the coordinates fail which is ideal, input
A1:E1)
2. A2-C2 for Battleship (input A2:D2 on fail)
3. A3-B3 for Destroyer (input A3:C3 on fail)
4. A4-B4 for Submarine (input A4:C4 on fail)
5. A5-A5 for Patrol Boat (input A5:B5 on fail)
iv. Execution Steps:
1. Input the given values for the Carrier. Check that the ship’s
coordinates were rejected
a. If the ship is accepted, stop the test and mark that the
failure came from the Carrier entry. End the test and test
again later.
b. If the ship is rejected, input valid coordinates for the Carrier
to continue
2. Input the given values for the Battleship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Battleship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Battleship to continue
3. Input the given values for the Destroyer. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Destroyer entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Destroyer to continue
4. Input the given values for the Submarine. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Submarine entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Submarine to continue
5. Input the given values for the Patrol ship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Patrol ship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the Patrol
boat to continue
v. Output Values:
1. All ships should have rejected the initial, purposely incorrect
values listed above
2. On invalid shot, an error message stating which coordinates are
invalid.
vi. Postconditions: All ships should have been placed correctly after inputting
the CORRECT VALUES provided above
TC-3.3d: Too Short Ship Input Test X Direction
i. Test Case: All ships must accept the given input values and be placed
properly on the board
ii. Preconditions:
1. It is the beginning of a player’s ship placement period
iii. Input Values:
1. A1-A6 for Carrier (if/when the coordinates fail which is ideal, input
A1:A5)
2. B1-B5 for Battleship (input B1:B4 on fail)
3. C1-C4 for Destroyer (input C1:C3 on fail)
4. D1-D4 for Submarine (input D1:D3 on fail)
5. E1-E3 for Patrol Boat (input E1:E2 on fail)
iv. Execution Steps:
1. Input the given values for the Carrier. Check that the ship’s
coordinates were rejected
a. If the ship is accepted, stop the test and mark that the
failure came from the Carrier entry. End the test and test
again later.
b. If the ship is rejected, input valid coordinates for the Carrier
to continue
2. Input the given values for the Battleship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Battleship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Battleship to continue
3. Input the given values for the Destroyer. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Destroyer entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Destroyer to continue
4. Input the given values for the Submarine. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Submarine entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Submarine to continue
5. Input the given values for the Patrol ship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Patrol ship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the Patrol
boat to continue
v. Output Values:
1. All ships should have rejected the initial, purposely incorrect
values listed above
2. On invalid shot, an error message stating which coordinates are
invalid.
vi. Postconditions: All ships should have been placed correctly after inputting
the CORRECT VALUES provided above


TC-3.3e: Too Short Shop Input Test Y Direction
i. Test Case: All ships must accept the given input values and be placed
properly on the board
ii. Preconditions:
1. It is the beginning of a player’s ship placement period
iii. Input Values:
1. A1-F1 for Carrier (if/when the coordinates fail which is ideal, input
A1:E1)
2. A2-E2 for Battleship (input A2:D2 on fail)
3. A3-D3 for Destroyer (input A3:C3 on fail)
4. A4-D4 for Submarine (input A4:C4 on fail)
5. A5-C5 for Patrol Boat (input A5:B5 on fail)
iv. Execution Steps:
1. Input the given values for the Carrier. Check that the ship’s
coordinates were rejected
a. If the ship is accepted, stop the test and mark that the
failure came from the Carrier entry. End the test and test
again later.
b. If the ship is rejected, input valid coordinates for the Carrier
to continue
2. Input the given values for the Battleship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Battleship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Battleship to continue
3. Input the given values for the Destroyer. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Destroyer entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Destroyer to continue
4. Input the given values for the Submarine. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Submarine entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the
Submarine to continue
5. Input the given values for the Patrol ship. Check that the input
coordinates were rejected
a. If the ship is rejected, stop the test and mark that the
failure came from the Patrol ship entry. End the test and
test again later.
b. If the ship is rejected, input valid coordinates for the Patrol
boat to continue
v. Output Values:
1. All ships should have rejected the initial, purposely incorrect
values listed above
2. On invalid shot, an error message stating which coordinates are
invalid.
vi. Postconditions: All ships should have been placed correctly after inputting
the CORRECT VALUES provided above

------------------------------------------------------------------------------
# TC-3.4


------------------------------------------------------------------------------
TC-3.5a: 2 Ships In Same Cell Test (Middle Edge)
i. Test Case: Tests whether one player can place 2 ships in the same cell,
when placing the edge of the ship being placed on the middle of the ship
already placed.
ii. Preconditions:
1. The game is in the ship placement phase.
2. Player 0 has carrier placed A1-A5.
iii. Input Values:
1. A3-D3
iv. Execution Steps:
1. Place player 0’s battleship A3-D3
v. Output Values:
1. An error message stating that player 0’s battleship may not be
placed there.
vi. Postconditions:
1. Player 0 is able to keep placing ships.
2. Player 0 only has their carrier placed A1-A5.

TC-3.5b: 2 Ships In Same Cell Test (Edge Edge)
i. Test Case: Tests whether one player can place 2 ships in the same cell,
when placing the edge of the ship being placed on the edge of the ship
already placed.
ii. Preconditions:
1. The game is in the ship placement phase.
2. Player 0 has battleship placed A1-A4.
iii. Input Values:
1. A1-C1
iv. Execution Steps:
1. Place player 0’s submarine A1-C1.
v. Output Values:
1. An error message stating that player 0’s submarine may not be
placed there.
vi. Postconditions:
1. Player 0 is able to keep placing ships.
2. Player 0 only has their battleship placed A1-A4.

TC-3.5c: 2 Ships In Same Cell Test (Middle Middle)
i. Test Case: Tests whether one player can place 2 ships in the same cell,
when placing the middle of the ship being placed on the middle of the ship
already placed.
ii. Preconditions:
1. The game is in the ship placement phase.
2. Player 0 has submarine placed B1-B3.
iii. Input Values:
1. A2-D2
iv. Execution Steps:
1. Place player 0’s battleship A2-D2
v. Output Values:
1. An error message stating that player 0’s battleship may not be
placed there.
vi. Postconditions:
1. Player 0 is able to keep placing ships.
2. Player 0 only has their submarine placed B1-B3.

TC-3.5d: 2 Ships In Same Cell Test (on top)
i. Test Case: Tests whether one player can place 2 ships in the same cell,
when placing a ship entirely on top of another ship.
ii. Preconditions:
1. The game is in the ship placement phase.
2. Player 0 has carrier placed A1-A5.
iii. Input Values:
1. A1-B2
iv. Execution Steps:
1. Place player 0’s patrol boat A1-A2
v. Output Values:
1. An error message stating that player 0’s battleship may not be
placed there.
vi. Postconditions:
1. Player 0 is able to keep placing ships.
2. Player 0 only has their carrier placed A1-A5

------------------------------------------------------------------------------
TC-4.2a: Missile Boundary Testing
i. Test Case: This test will attempt to fire a shot at a coordinate that is not
on the board
ii. Preconditions:
1. All ships have been placed and the game is currently in the firing
phase
iii. Input Values: [(A to J):-5, (A to J):-1, (A to J):11, (A to J):15, K:1, L:1…,
Z:1]
iv. Execution Steps:
1. Enter first Coordinate (A-5)
2. Look for error message
3. Ensure the player’s turn to fire hasn’t ended
4. Repeat with the rest of the list of coordinates starting with B-5 and
ending at Z1
v. Output Values: An error message stating that the coordinate is not within
the firing range should be displayed
vi. Postconditions: Current player’s turn should not end and they should be
prompted to enter another 

------------------------------------------------------------------------------
TC-5.1a: Ships Must Be Placed Before Firing Phase Begins
i. Test Case: This test will make sure that both players have placed all of
their ships before any player begins firing
ii. Preconditions:
1. Player A and Player B haven’t finished placing all ships.
iii. Input Values:
1. A1:A5 for Carrier
2. B1:B4 for Battleship
3. C1:C3 for Destroyer
4. D1:D3 for Submarine
5. E1:E2 for Patrol Boat
iv. Execution Steps:
1. Both players will input above coordinates for their ships in the
placement phase
v. Output Values:
1. Firing round start message
vi. Postconditions:
1. Both players should have all 5 ships placed on the board
2. The game is moved into the firing phase

 --------------------------------------------------------------------------
 
TC-Non1a TEST-WINDOWS-CLIENT
4.1.1. Test Case: Test to see that when the game is launched and played on a Microsoft
Windows machine, it will run with no problems.
4.1.2. Preconditions: The tester is running a machine with Microsoft Windows.
4.1.3. Input Values: A full game is played on this machine.
4.1.4. Execution Steps: The tester should use a machine with Microsoft Windows. They
should launch the client program, join a game server, and play a full game of
battleship to completion.
4.1.5. Post Conditions: The game should exit and the system should function as it was
before the game was run.

TC-Non1b TEST-LINUX-CLIENT
4.2.1. Test Case: Test to see that when the game is launched and played on a Ubuntu
Linux machine, it will run with no problems.
4.2.2. Preconditions: The tester is running a machine with Ubuntu Linux.
4.2.3. Input Values: A full game is played on this machine.
4.2.4. Execution Steps: The tester should use a machine with Ubuntu Linux. They should
launch the client program, join a game server, and play a full game of battleship to
completion.
4.2.5. Post Conditions: The game should exit and the system should function as it was
before the game was run.

TC-Non1c TEST-OSX-CLIENT
4.3.1. Test Case: Test to see that when the game is launched and played on an Apple
OSX machine, it will run with no problems.
4.3.2. Preconditions: The tester is running a machine with Apple OSX.
4.3.3. Input Values: A full game is played on this machine.
4.3.4. Execution Steps: The tester should use a machine with Apple OSX. They should
launch the client program, join a game server, and play a full game of battleship to
completion.
4.3.5. Post Conditions: The game should exit and the system should function as it was
before the game was run.

TC-NON2 TEST-JAVA-SERVER
4.50.1. Test Case: This is a test to see what happens when the server program is compiled
with -javac. This is to verify that the server is written in Java.
4.50.2. Preconditions: The system must have Java and the Java JDK installed.
4.50.3. Input Values: Running the -javac and -java commands.
4.50.4. Execution Steps: Compile the server program using -javac and run the server
program using -java.
4.50.5. Post Conditions: The server program will compile without error after the -javac
command and should run after -java command

TC-Non3a The client can reach the server.
ii) Preconditions: The client must have access to internet.
iii) Input Values: The client has to launch the program and choose to connect
to the server.
iv) Execution Steps: Launch program and run as client and connect to
server.
v) Output Values: Expected output would be a successful launch message,
can also receive error message if unsuccessful or an error occurred while
trying to reach the server.
vi) Post Conditions: If successful, the client will be connected to the server, if
not then the client is not connected to the server.

TC-Non3b: 2 clients can reach the server at once.
ii) Preconditions: Both clients must have access to internet.
iii) Input Values: The clients must launch the battleship program and choose
to connect to the server.
iv) Execution Steps: Launch battleship program and run as client and
connect to server.
v) Output Values: Expected output would be a successful launch message
for both clients, can also receive error message if unsuccessful or an
error occurred while trying to reach the server.
vi) Post Conditions: If successful, both clients will be connected to the server,
if not then the clients are not connected to the server