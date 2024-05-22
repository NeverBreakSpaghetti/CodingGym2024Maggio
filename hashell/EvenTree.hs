-- Problem: https://www.hackerrank.com/challenges/even-tree

module Main where

data Edge a = Edge {to :: a, from :: a}
  deriving (Show, Eq)

data Tree a = Tree {root :: a, edges :: [Edge a]}
  deriving (Show)

data NodeData = NodeData {cuts :: Int, children :: Int}

-- | Get the number of nodes in the tree.
nodeCount :: Tree a -> Int
nodeCount (Tree _ []) = 0
nodeCount (Tree _ edges) = 1 + length edges

-- | Return the children of the given node.
childrenOf :: Eq a => Tree a -> a -> [a]
childrenOf tree x =
  map to
    . filter ((== x) . from)
    . edges
    $ tree

--foldTree :: (a -> b -> b) -> a -> Tree b -> a
--foldTree f z tree =
  

evenNodes_ ::
  Eq a =>
  -- | The tree
  Tree a ->
  -- | The starting node
  a ->
  NodeData
evenNodes_ tree =
  foldl f (NodeData 0 0)
    . map (evenNodes_ tree)
    . childrenOf tree
  where
    f (NodeData cuts children) (NodeData childCuts childChildren) =
      let newChildren = children + childChildren + 1
          canCut = even newChildren
          newCuts = cuts + childCuts + if canCut then 1 else 0
       in NodeData newCuts newChildren

evenNodes :: Eq a => Tree a -> Int
evenNodes tree@(Tree root edges) = cuts $ evenNodes_ tree root

mkTree :: [(Int, Int)] -> Tree Int
mkTree = Tree 1 . map (uncurry Edge)

tree :: Tree Int
tree =
  mkTree
    [ (2, 1),
      (3, 1),
      (4, 3),
      (5, 2),
      (6, 1),
      (7, 2),
      (8, 6),
      (9, 8),
      (10, 8)
    ]

main :: IO ()
main = print $ nodeCount tree
