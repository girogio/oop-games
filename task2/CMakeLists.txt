cmake_minimum_required(VERSION 3.24)
project(minesweeper)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_FLAGS "-lncurses -g")


set(SOURCE_FILES Window.cpp BoardWindow.cpp Board.cpp Tile.cpp Game.cpp InfoWindow.cpp)

add_executable(minesweeper main.cpp ${SOURCE_FILES})
