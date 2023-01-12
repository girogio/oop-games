//
// Created by Giorgio Grigolo on 11/01/2023.
//

#ifndef MINESWEEPER_TILE_H
#define MINESWEEPER_TILE_H

#include <string>

class Tile {

private:
    int adjacentBombs;
    bool isVisible, isBomb, isFlagged;

public:
    Tile();

    std::string getStringValue() const;

    void setValue(int value);
    int getValue() const;

    void setIsVisible(bool visible);
    bool getIsVisible() const;

    void setIsBomb(bool isBomb);
    bool getIsBomb() const;

    void setIsFlagged(bool isFlagged);
    bool getIsFlagged();

};


#endif //MINESWEEPER_TILE_H
