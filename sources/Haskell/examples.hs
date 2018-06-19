module Main

where

-- import Test.Hspec

times x y = x * y

timesVar = times

apply func arg = func arg

timess x = (\y -> x * y)

times3 = times 3

fib x = if x < 2 then x else fib (x-1) + fib (x-2)

fibb 0 = 0
fibb 1 = 1
fibb x = fibb (x-1) + fibb (x-2)

data Tree =
       Node Tree Tree
     | Leaf Int
     deriving Show

myTree = Node (Node (Leaf 4) (Node (Leaf 7) (Leaf 1))) (Leaf 3)

treeSum (Leaf x) = x
treeSum (Node m n) = treeSum m + treeSum n





(>.>) x f = f x

-- the tests from the talk
-- here in HSpec syntax

main :: IO ()
main = print "Hello"

{-
tests :: IO ()
tests = hspec $ do
  describe "Examples from the talk" $ do
      it "timesVar can be used just like times" $ do
         timesVar 3 5 `shouldBe` 15

      it "apply applies the function to the argument" $ do
         apply (\x -> 3 * x) 5 `shouldBe` 15

      it "timess can be used just like times" $ do
         timess 3 5 `shouldBe` 15

      it "times3 can be used just like times 3" $ do
         times3 5 `shouldBe` 15

      it "filter picks the matching elements" $ do
         filter (\x -> x `mod` 2 == 0) [1,2,3,4] `shouldBe` [2,4]

      it "map applies the function to each list entry" $ do
         map (\x -> x + 5) [1,2,3,4] `shouldBe` [6,7,8,9]

      it "fold gathers all values into one" $ do
         foldl (*) 1 [2,3,4,5] `shouldBe` 120

      it "the summation example" $ do
         foldl (+) 0 (map (\x -> x*x) [1..10]) `shouldBe` 385

      it "the summation example in forward direction" $ do
         [1..10] >.> map (\x -> x*x) >.> foldl (+) 0 `shouldBe` 385

      it "fib" $ do
         fib 7 `shouldBe` 13

      it "fibb works just like fib" $ do
         fibb 7 `shouldBe` 13

      it "summing the leaves of a tree" $ do
         treeSum myTree `shouldBe` 15
-}
