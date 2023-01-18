//
// Created by Giorgio Grigolo on 11/01/2023.
//

#ifndef MINESWEEPER_BOARD_H
#define MINESWEEPER_BOARD_H


#include "Tile.h"

class Board {

private:
    Tile tiles[16][16];

    int bombCount;

public:

    explicit Board(int bombCount);

    Tile &getTile(int x, int y);

    void revealTile(int x, int y);

    void revealAll();

    int getAdjacentBombs(int x, int y);

    int getNonBombTileCount();

    int getBombCount() const;
};


#endif //MINESWEEPER_BOARD_H
