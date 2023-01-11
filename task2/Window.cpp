//
// Created by Giorgio Grigolo on 11/01/2023.
//

#include <cstdlib>
#include "Window.h"

void Window::init(int w, int h, int sx, int sy) {

    this->x = 0, this->y = 0;
    this->width = w;
    this->height = h;
    this->start_x = sx;
    this->start_y = sy;

    win = newwin(height, width, start_y, start_x);

    refresh();
    box(win, 0, 0);
    noecho();
    raw();
    nodelay(this->win, true);
    wmove(Window::win, 1, 3);

    if (!has_colors()) {
        endwin();
        printf("Your terminal does not support color\n");
        exit(1);
    } else {
        start_color();
        init_pair(1, COLOR_WHITE, COLOR_BLACK);
        init_pair(2, COLOR_CYAN, COLOR_BLACK);
        init_pair(3, COLOR_GREEN, COLOR_BLACK);
        init_pair(4, COLOR_RED, COLOR_BLACK);
        init_pair(5, COLOR_RED, COLOR_BLACK);
        init_pair(6, COLOR_RED, COLOR_BLACK);
        init_pair(7, COLOR_RED, COLOR_BLACK);
        init_pair(8, COLOR_RED, COLOR_BLACK);
        init_pair(9, COLOR_RED, COLOR_BLACK);
    }

}