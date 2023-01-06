#include "game.h"

int main(void)
{

  initscr();
  Game game = Game();

  game.loop();
  endwin();
  return 0;
}