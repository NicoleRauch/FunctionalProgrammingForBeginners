module Main

where

times x y = x * y

timesVar = times

wendeAn func arg = func arg

timess x = (\y -> x * y)

times3 = times 3

fib x = if x < 2 then x else fib (x-1) + fib (x-2)

fibb 0 = 0
fibb 1 = 1
fibb x = fibb (x-1) + fibb (x-2)

data Tree =
       Node Tree Tree
     | Leaf Int

myTree = Node (Node (Leaf 4) (Node (Leaf 7) (Leaf 1))) (Leaf 3)

treeSum (Leaf x) = x
treeSum (Node m n) = treeSum m + treeSum n

(>.>) x f = f x

tests   = timesVar 3 5 == 15
       && wendeAn (\x -> 3 * x) 5 == 15
       && timess 3 5 == 15
       && times3 5 == 15
       && filter (\x -> x `mod` 2 == 0) [1,2,3,4] == [2,4]
       && map (\x -> x + 5) [1,2,3,4] == [6,7,8,9]
       && foldl (*) "x" [2,3,4,5] == 120
       && foldl (+) 0 (map (\x -> x*x) [1..10])
       && [1..10] >.> map (\x -> x*x) >.> foldl (+) 0 == 385
       && fib 7 == 13
       && fibb 7 == 13
       && treeSum myTree == 15

main = putStrLn ("Alles richtig: " ++ (show tests))

