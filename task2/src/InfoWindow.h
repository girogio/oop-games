//
// Created by Giorgio Grigolo on 11/01/2023.
//

#ifndef MINESWEEPER_INFOWINDOW_H
#define MINESWEEPER_INFOWINDOW_H

#include "Window.h"
#include "Board.h"
#include "string"

class InfoWindow : Window {

private:
    std::string title;
    int xc, yc;

public:
    InfoWindow();

    void draw() override;

    void update() override;

    void setInfo(std::string title, int xc, int yc);
};


#endif
