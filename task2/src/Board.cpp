//
// Created by Giorgio Grigolo on 11/01/2023.
//

#include "Board.h"

Board::Board(int bombCount) {

    this->bombCount = bombCount;

    // Initialize all tiles
    for (auto &tile: tiles) {
        for (auto &j: tile) {
            j = Tile();
        }
    }

    generateBombs(bombCount);

    // Set the value of each tile
    for (int y = 0; y < 16; y++) {
        for (int x = 0; x < 16; x++) {
            getTile(x, y).setValue(getAdjacentBombs(x, y));
        }
    }

}


void Board::generateBombs(int n) {


    srand(time(NULL));

    for (int i = 0; i < n; i++) {
        int x = rand() % 16;
        int y = rand() % 16;

        if (getTile(x, y).getIsBomb()) {
            i--;
        } else {
            getTile(x, y).setIsBomb(true);
        }
    }

}

Tile &Board::getTile(int x, int y) {
    return tiles[y][x];
}

void Board::revealTile(int x, int y) {

    if (!getTile(x, y).getIsVisible()) {
        getTile(x, y).setIsVisible(true);

        if (getTile(x, y).getValue() == 0) {
            if (x > 0)
                revealTile(x - 1, y);
            if (x < 15)
                revealTile(x + 1, y);
            if (y > 0)
                revealTile(x, y - 1);
            if (y < 15)
                revealTile(x, y + 1);
            if (x > 0 && y > 0)
                revealTile(x - 1, y - 1);
            if (x > 0 && y < 15)
                revealTile(x - 1, y + 1);
            if (x < 15 && y > 0)
                revealTile(x + 1, y - 1);
            if (x < 15 && y < 15)
                revealTile(x + 1, y + 1);
        }
    }
}

void Board::revealAll() {
    for (int y = 0; y < 16; y++) {
        for (int x = 0; x < 16; x++) {
            getTile(x, y).setIsVisible(true);
        }
    }
}

int Board::getAdjacentBombs(int x, int y) {
    int count = 0;
    for (int i = y - 1; i <= y + 1; i++)
        for (int j = x - 1; j <= x + 1; j++)
            if (i >= 0 && i < 16 && j >= 0 && j < 16)
                if (getTile(j, i).getIsBomb() && !(j == x && i == y))
                    count++;
    return count;
}

int Board::getNonBombTileCount() {
    int count = 0;
    for (int y = 0; y < 16; y++) {
        for (int x = 0; x < 16; x++) {
            if (!getTile(x, y).getIsBomb() && getTile(x, y).getIsVisible()) {
                count++;
            }
        }
    }
    return count;
}

int Board::getBombCount() const {
    return bombCount;
}
