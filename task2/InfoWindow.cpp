//
// Created by Giorgio Grigolo on 12/01/2023.
//

#include "InfoWindow.h"

InfoWindow::InfoWindow() : Window() {
    this->width = 20;
    this->height = 20;
    this->xc = 0;
    this->yc = 0;
    this->start_x = 0;
    this->start_y = (LINES - this->height) / 2;
    init(this->width, this->height, this->start_x, this->start_y);
    InfoWindow::draw();
    InfoWindow::update();
}

void InfoWindow::setInfo(std::string title, int xc, int yc) {
    this->title = title;
    this->xc = xc;
    this->yc = yc;
    update();
}

void InfoWindow::draw() {
    win = newwin(this->height, this->width, this->start_y, this->start_y); // new window
    box(win, 0, 0);                                                      // box it
    refresh();                                                           // refresh the screen
}

void InfoWindow::update() {
    mvwprintw(win, this->height / 2 - 1, 1, "X-coordinate: %2d", xc); // print the x-coordinate
    mvwprintw(win, this->height / 2, 1, "Y-coordinate: %2d", yc);     // print the y-coordinate
    mvwprintw(win, 1, this->width / 2 - 5, title.c_str());            // print the title
    wrefresh(win);
}