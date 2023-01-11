//
// Created by Giorgio Grigolo on 11/01/2023.
//

#include "Tile.h"

Tile::Tile() {
    this->isBomb = false;
    this->isFlagged = false;
    this->isVisible = false;
    this->adjacentBombs = 0;
}

std::string Tile::getStringValue() {
    if (!isVisible) {
        return "--";
    } else if (isBomb) {
        return "XX";
    } else if (adjacentBombs <= 9) {
        return "0" + std::to_string(adjacentBombs);
    } else {
        return std::to_string(adjacentBombs);
    }
}

void Tile::setValue(int value) {
    Tile::adjacentBombs = value;
}

int Tile::getValue() {
    return adjacentBombs;
}

void Tile::setIsVisible(bool visible) {
    Tile::isVisible = visible;
}

bool Tile::getIsVisible() {
    return isVisible;
}


