module Main

where

times (x, y) = x * y

apply func arg = func arg

timess x y = x * y

tests   = times (3,5) == 15
       && apply (\ x -> 3 * x) 5 == 15
       && timess 3 5 == 15

main = putStrLn ("Alles richtig: " ++ (show tests))

