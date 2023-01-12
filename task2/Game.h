//
// Created by Giorgio Grigolo on 11/01/2023.
//

#ifndef MINESWEEPER_GAME_H
#define MINESWEEPER_GAME_H


#include "BoardWindow.h"

class Game {

private:
    BoardWindow boardWindow;

public:
    Game();

    void start();
};


#endif //MINESWEEPER_GAME_H
