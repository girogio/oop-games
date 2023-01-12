//
// Created by Giorgio Grigolo on 11/01/2023.
//

#include "BoardWindow.h"

BoardWindow::BoardWindow() : Window() {
    init(51, 18, (COLS - 51) / 2, (LINES - 18) / 2);
    update();
    wmove(stdscr, start_y + 1, start_x + 3);
}

void BoardWindow::draw() {
    refresh();
    box(win, 0, 0);
    wrefresh(win);
    refresh();
    update();
    wmove(Window::win, 1, 3);
    wrefresh(Window::win);
}

void BoardWindow::update() {
    for (int y = 0; y < 16; y++) {
        for (int x = 0; x < 16; x++) {
            if (!board.getTile(x, y).getIsVisible()) {
                wattron(win, COLOR_PAIR(1));
                mvwprintw(win, y + 1, x * 3 + 2, board.getTile(x, y).getStringValue().c_str());
                wattroff(win, COLOR_PAIR(1));
            } else if (board.getTile(x, y).getIsBomb()) {
                wattron(win, COLOR_PAIR(4));
                mvwprintw(win, y + 1, x * 3 + 2, board.getTile(x, y).getStringValue().c_str());
                wattroff(win, COLOR_PAIR(4));
            } else {
                wattron(win, COLOR_PAIR(board.getTile(x, y).getValue() + 1));
                mvwprintw(win, y + 1, x * 3 + 2, board.getTile(x, y).getStringValue().c_str());
                wattroff(win, COLOR_PAIR(board.getTile(x, y).getValue() + 1));
            }
        }
    }
    wrefresh(Window::win);
}

void BoardWindow::move(int ch) {
    switch (ch) {
        case 'w':
        case 'k':
            if (this->y > 0 && this->y < 16)
                this->y--;
            break;
        case 'a':
        case 'h':
            if (this->x > 0)
                this->x--;
            break;
        case 's':
        case 'j':
            if (this->y < 15)
                this->y++;
            break;
        case 'd':
        case 'l':
            if (x < 15)
                x++;
            break;
        case ' ':
        case '\n':
            if (!board.getTile(x, y).getIsBomb()) {
                board.revealTile(x, y);
            } else {
                board.revealAll();
                update();
//                info_win.game_over();
                endwin();
                exit(0);
            }

            if (board.getRemainingHiddenBombs() == 0) {
                board.revealAll();
                update();
                endwin();
                exit(0);
                break;
                case 'b':
                    board.getTile(x, y).setIsBomb(true);
                break;
            }

//    info_win.set_info(x, y, board.get_adjacent_bombs(x, y));
            wmove(stdscr, start_y + y + 1, start_x + x * 3 + 3);
            update();
    }