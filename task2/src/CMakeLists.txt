cmake_minimum_required(VERSION 3.24)
project(minesweeper)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_FLAGS "-lncurses -g")

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ../bin)

set(SOURCE Tile.cpp Board.h BoardWindow.cpp InfoWindow.cpp Window.cpp Game.cpp Board.cpp)

add_executable(minesweeper main.cpp ${SOURCE})
