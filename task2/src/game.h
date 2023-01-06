#include "info_win.h"
#include "board_win.h"
#include "ncurses.h"

class Game
{
private:
  InfoWindow info_win = InfoWindow(20, 20);
  BoardWindow board_win;

public:
  void loop()
  {
    int ch;
    while ((ch = wgetch(board_win.win)) != 'q')
    {
      board_win.move(ch, info_win);
    }
  }
};