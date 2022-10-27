#include "ncurses.h"
#include "board.h"

class BoardWindow
{
private:
  int x = 0;
  int y = 0;
  int width, height;
  int startx, starty;
  Board board;

public:
  BoardWindow()
  {
    width = 51;
    height = 18;
    this->starty = (LINES - height) / 2;
    this->startx = (COLS - width) / 2;
    noecho();
    raw();
    nodelay(win, true);
    if (!has_colors())
    {
      exit(1);
    }
    else
    {
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
    draw();
  }
  WINDOW *win;

  void draw()
  {
    win = newwin(this->height, this->width, this->starty, this->startx);
    refresh();
    box(win, 0, 0);
    wrefresh(win);
    refresh();
    update();
    wmove(win, 1, 3);
  }

  void update()
  {
    for (int y = 0; y < 16; y++)
    {
      for (int x = 0; x < 16; x++)
      {
        if (!board.get_tile(x, y).get_visible())
        {
          wattron(win, COLOR_PAIR(1));
          mvwprintw(win, y + 1, x * 3 + 2, board.get_tile(x, y).display_value().c_str());
          wattroff(win, COLOR_PAIR(1));
        }
        else if (board.get_tile(x, y).is_bomb())
        {
          wattron(win, COLOR_PAIR(4));
          mvwprintw(win, y + 1, x * 3 + 2, board.get_tile(x, y).display_value().c_str());
          wattroff(win, COLOR_PAIR(4));
        }
        else
        {
          wattron(win, COLOR_PAIR(board.get_tile(x, y).get_value() + 1));
          mvwprintw(win, y + 1, x * 3 + 2, board.get_tile(x, y).display_value().c_str());
          wattroff(win, COLOR_PAIR(board.get_tile(x, y).get_value() + 1));
        }
      }
    }
    refresh();
    wrefresh(win);
  }

  void
  move(int ch, InfoWindow info_win)
  {
    switch (ch)
    {
    case 'w':
    case 'k':
      if (y > 0 && y < 16)
        y--;
      break;
    case 'a':
    case 'h':
      if (x > 0)
        x--;
      break;
    case 's':
    case 'j':
      if (y < 15)
        y++;
      break;
    case 'd':
    case 'l':
      if (x < 15)
        x++;
      break;
    case ' ':
    case '\n':
      if (!board.get_tile(x, y).is_bomb())
      {
        board.reveal_tile(x, y);
      }
      else
      {
        board.reveal_all();
        update();
        info_win.game_over();
        exit(0);
      }
      break;
    case 'b':
      board.get_tile(x, y).set_bomb(true);
      break;
    }

    update();
    info_win.set_info(x, y, board.get_adjacent_bombs(x, y));
    wmove(win, y + 1, x * 3 + 3);
  }
};
