#include "ncurses.h"
#define DEBUG

class InfoWindow
{
private:
  int x = 0, y = 0;
#ifdef DEBUG
  int adjacent_bombs = 0;
#endif
  int width, height;
  int startx, starty;

public:
  InfoWindow(int height, int width)
  {
    this->height = height;
    this->width = width;
    this->starty = (LINES - height) / 2; // center the window vertically
    this->startx = 0;                    // left align the window
    draw();                              // draw the window
    update();                            // fill with initial data
  }
  WINDOW *win;

  void draw()
  {
    win = newwin(this->height, this->width, this->starty, this->startx); // new window
    box(win, 0, 0);                                                      // box it
    refresh();                                                           // refresh the screen
  }

  void update()
  {
    mvwprintw(win, this->height / 2 - 1, 1, "X-coordinate: %2d", x); // print the x-coordinate
    mvwprintw(win, this->height / 2, 1, "Y-coordinate: %2d", y);     // print the y-coordinate
#ifdef DEBUG
    mvwprintw(win, this->height / 2 + 1, 1, "Adjacent_bombs: %d", adjacent_bombs); // cheats: activate
#endif
    wrefresh(win);
  }

  void game_over()
  {
    mvwprintw(win, 1, this->width / 2 - 5, "Game over.");
    wgetch(win);
    endwin();
  }

  void set_info(int x, int y, int adjacent_bombs)
  {
    this->x = x;
    this->y = y;
#ifdef DEBUG
    this->adjacent_bombs = adjacent_bombs;
#endif
    update();
  }
};
