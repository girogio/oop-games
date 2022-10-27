#include <string>
using namespace std;
class Tile
{
private:
  int value;
  bool visible, is_bombv;

public:
  void set_value(int val)
  {
    value = val;
  }

  int get_value()
  {
    return value;
  }

  string display_value()
  {
    if (visible)
    {
      if (is_bombv)
      {
        return "XX";
      }
      else if (value < 10)
      {
        return "0" + to_string(value);
      }
      else
      {
        return to_string(value);
      }
    }
    else
      return "--";
  }

  void set_visible(bool vis)
  {
    visible = vis;
  }

  bool get_visible()
  {
    return visible;
  }

  void set_bomb(bool bomb)
  {
    is_bombv = bomb;
  }

  bool is_bomb()
  {
    return this->is_bombv;
  }
};
