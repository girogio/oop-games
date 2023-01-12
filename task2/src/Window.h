//
// Created by Giorgio Grigolo on 11/01/2023.
//

#ifndef MINESWEEPER_WINDOW_H
#define MINESWEEPER_WINDOW_H

#include <ncurses.h>

class Window {

protected:
    int x;
    int y;
    int width;
    int height;
    int start_y;
    int start_x;

public:

    WINDOW *win;
    virtual void draw() = 0;

    virtual void update() = 0;

    void init(int width, int height, int start_y, int start_x);
};


#endif //MINESWEEPER_WINDOW_H
