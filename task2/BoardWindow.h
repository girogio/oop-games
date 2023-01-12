//
// Created by Giorgio Grigolo on 11/01/2023.
//

#ifndef MINESWEEPER_BOARDWINDOW_H
#define MINESWEEPER_BOARDWINDOW_H

#include "Window.h"
#include "Board.h"
#include "InfoWindow.h"

class BoardWindow : Window {

private:
    Board board = Board(2);
    InfoWindow infoWindow;

public:
    BoardWindow();

    void draw() override;

    void update() override;

    void move(int ch);

};


#endif //MINESWEEPER_BOARDWINDOW_H
