#include "tile.h"

class Board
{

public:
  Tile tiles[16][16];
  Board()
  {
    generate_bombs();

    for (int y = 0; y < 16; y++)
    {
      for (int x = 0; x < 16; x++)
      {
        get_tile(x, y).set_visible(false);
        get_tile(x, y).set_value(get_adjacent_bombs(x, y));
      }
    }
  }

  void generate_bombs()
  {
    srand(time(0));

    for (int i = 0; i < 40; i++)
    {
      int x = rand() % 16;
      int y = rand() % 16;
      if (get_tile(x, y).is_bomb())
      {
        i--;
      }
      else
      {
        get_tile(x, y).set_bomb(true);
      }
    }
  }

  Tile &get_tile(int x, int y)
  {
    return tiles[y][x];
  }

  void reveal_tile(int x, int y)
  {
    if (!get_tile(x, y).get_visible())
    {
      get_tile(x, y).set_visible(true);

      if (get_tile(x, y).get_value() == 0)
      {
        if (x > 0)
          reveal_tile(x - 1, y);
        if (x < 15)
          reveal_tile(x + 1, y);
        if (y > 0)
          reveal_tile(x, y - 1);
        if (y < 15)
          reveal_tile(x, y + 1);
        if (x > 0 && y > 0)
          reveal_tile(x - 1, y - 1);
        if (x > 0 && y < 15)
          reveal_tile(x - 1, y + 1);
        if (x < 15 && y > 0)
          reveal_tile(x + 1, y - 1);
        if (x < 15 && y < 15)
          reveal_tile(x + 1, y + 1);
      }
    }
  }

  void reveal_all()
  {
    for (int y = 0; y < 16; y++)
    {
      for (int x = 0; x < 16; x++)
      {
        get_tile(x, y).set_visible(true);
      }
    }
  }

  int get_adjacent_bombs(int x, int y)
  {
    int count = 0;
    for (int i = y - 1; i <= y + 1; i++)
      for (int j = x - 1; j <= x + 1; j++)
        if (i >= 0 && i < 16 && j >= 0 && j < 16)
          if (get_tile(j, i).is_bomb() && !(j == x && i == y))
            count++;
    return count;
  }
};