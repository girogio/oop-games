//
// Created by Giorgio Grigolo on 11/01/2023.
//

#include "Game.h"

Game::Game() {
    boardWindow = BoardWindow();
}

void Game::start() {

    int ch;

    while ((ch = getch()) != 'q') {
        this->boardWindow.move(ch);
    }


    endwin();
}