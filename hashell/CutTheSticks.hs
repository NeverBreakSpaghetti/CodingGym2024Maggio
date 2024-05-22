-- Problem: https://www.hackerrank.com/challenges/cut-the-sticks

module Main where

import Data.Array

cutTheSticks :: [Int] -> [Int]
cutTheSticks [] = []
cutTheSticks sticks =
  let freqs = accumArray (+) 0 (1, 1000) [(i, 1) | i <- sticks]
      stickCount = length sticks
   in init
        . map (stickCount -)
        . scanl (+) 0
        . filter (> 0)
        . elems
        $ freqs

main :: IO ()
main = do
  print $ cutTheSticks [6, 4, 2, 1]
  print $ cutTheSticks [1, 2, 3, 4, 3, 3, 2, 1]
