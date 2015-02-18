{--

This is a stub for project 4. You need to define the functions after their type signatures below.
You can make as many auxilary functions as you want, but it is important that the functions below
are defined with the given names.

Be sure to put comments in this file and include a readme file!

--}

--test databases

database0 = [("Lana Turner", "Buster Keaton", 80000, "Virginia Dare"), ("Ted Hughes", "Edna Millay", 70000, "Virginia Dare"), ("Virginia Dare", "Laurence Sterne", 100000, "Edna Millay"), ("Buster Keaton", "Lana Turner", 80000, "Ingrid Joyce"), ("James Joyce", "Ingrid Joyce", 60000, "Root"), ("Vanessa Redgrave", "Michael Redgrave", 110000, "James Joyce"), ("Michael Redgrave", "Vanessa Redgrave", 40000, "Vanessa Redgrave"), ("Edna Millay", "Ted Hughes", 70000, "Root"), ("Laurence Sterne", "Virginia Dare", 60000, "James Joyce"), ("Ingrid Joyce", "James Joyce", 60000, "Virginia Dare")]
database1 = []
database2 = [("Carol", "Eric", 200000, "Bob"), ("Fran", "Dan", 200000, "Eric"), ("Bob", "Alex", 100000, "Alex"), ("Dan", "Fran", 150000, "Carol"), ("Alex", "Bob", 100000, "Root"), ("Eric", "Carol", 300000, "Dan")]
database3 = [("Carol", "Eric", 200000, "Root"), ("Fran", "Dan", 200000, "Root"), ("Bob", "Alex", 100000, "Alex"), ("Dan", "Fran", 150000, "Root"), ("Alex", "Bob", 100000, "Root"), ("Eric", "Carol", 300000, "Root")]
database4 = [("a","b",100000,"d"),("b","a",10,"Root"),("c","d",900000000000,"b"),("d","c",2000,"Root")]
-- type synonyms

type Employee = String
type Spouse = String
type Salary = Integer
type Manager = String
type Record = (Employee, Spouse, Salary, Manager)
type DB = [Record]

salary :: DB-> String-> Integer
salary [] name = 0
salary ((a,_,b,_):ds) name 
                 | a==name = b
                 | name=="Root" = 100000
                 | otherwise = salary ds name


getManager [] name = []
getManager ((a,_,_,d):ds) name
                 | a==name = d
                 | otherwise = getManager ds name

managerSalaryList db = removeDup (managerSalaryList' db db)

managerSalaryList' [] db = []
managerSalaryList' ((_,_,_,d):ds) db 
             | d/="Root" = (d,(salary db d)):(managerSalaryList' ds db)
             | otherwise = managerSalaryList' ds db

managerList db = removeDup (managerList' db db)

managerList' [] db = []
managerList' ((_,_,_,d):ds) db 
             | d/="Root" = d:(managerList' ds db)
             | otherwise = managerList' ds db

managerRankList [] db = []
managerRankList (x:xs) db = (x,length(managerSubList' db x)):(managerRankList xs db)

managerSubList' [] name = []
managerSubList' ((a,_,_,d):ds) name 
                 | d==name = a:(managerSubList' ds name)
                 | otherwise = managerSubList' ds name
                   

removeDup [] = []
removeDup (a:as)
             | (a `elem` as) = removeDup as
             | otherwise = a:(removeDup as)  


getSuper db name 
           |manager=="" = [] 
           |otherwise = manager:(getSuper db manager)
           where manager= getManager db name

largest [] db sal = False
largest (x:xs) db sal
              | ((salary db x) < sal) = True
              | otherwise= largest xs db sal

getSuperSubordinate [] db name = []
getSuperSubordinate ((a,b,_,d):ds) db name
              | (name/=b) && (name/="Root") && (name `elem` (getSuper db d)) = b:(getSuperSubordinate ds db name) 
              | otherwise = getSuperSubordinate ds db name


genMoney :: DB->DB->[((Employee, Spouse), Salary)]
genMoney [] [] = []
genMoney ((a,b,c,_):ds) ds1 = ((a,b),c'):(genMoney removed removed)
                       where removed= removeTuples b ds
                             otherSal= salary ds b
                             c'=c+otherSal

removeTuples a1 [] = []
removeTuples a1 ((a,b,c,d):ds)
               | a1==a = removeTuples a1 ds
               | otherwise = (a,b,c,d):(removeTuples a1 ds)

getMax :: Integer->[((Employee, Spouse), Salary)]->[(String,String)]
getMax k [] = []
getMax k (((a,b),c):xs)
       | c==k = (a,b):(getMax k xs)
       | otherwise = getMax k xs


maxVal x = maximum(snd(unzip x))

qsort [] = []
qsort ((a,b):xs) = (qsort great)++[(a,b)]++(qsort less)
         where
           less = filter ((<b).snd) xs
           great = filter ((>=b).snd) xs

qsort1 [] = []
qsort1 ((a,b):xs) = (qsort1 great)++[(a,b)]++(qsort1 less)
         where
           less = filter ((<a).fst) xs
           great = filter ((>=a).fst) xs


-- 1.

overpaid :: DB -> [String]
overpaid db = overpaid' db db

overpaid' [] db = []
overpaid' ((a,_,b,c):ds) db
                   | (salary db c) < b = a:(overpaid' ds db)
                   | otherwise = overpaid' ds db


grossly_overpaid :: DB -> [String]
grossly_overpaid db= grossly_overpaid' db db

grossly_overpaid' [] db = []
grossly_overpaid' ((a,_,b,c):ds) db
                   | (largest supermanagers db b) = a:(grossly_overpaid' ds db)
                   | otherwise = grossly_overpaid' ds db
                   where
                      supermanagers = (getSuper db c)


-- 2.

spouse_manager :: DB -> [String]
spouse_manager [] = []
spouse_manager ((a,b,_,d):ds)
                 | b == d = d:(spouse_manager ds)
                 | otherwise = spouse_manager ds

spouse_manager_super :: DB -> [String]
spouse_manager_super db = spouse_manager_super' db db

spouse_manager_super' [] db = []
spouse_manager_super' ((a,b,_,d):ds) db
                             | (b `elem` (getSuper db d)) = b:(spouse_manager_super' ds db)
                             | otherwise = spouse_manager_super' ds db

-- 3.

super_manager :: DB -> [String]
super_manager db = super_manager' superspouses db
                   where superspouses= spouse_manager_super db

super_manager' [] db = []
super_manager' (x:xs) db 
                 | ((getSuperSubordinate db db x) /= []) = x:(super_manager' xs db)
                 | otherwise = super_manager' xs db
               

-- 4.

nepotism :: DB -> [(String, String)]
nepotism db = nepotism' db db db

nepotism' x [] db = []
nepotism' [] ((a1,b1,c1,d1):ds1) db = nepotism' db ds1 db
nepotism' ((a,b,_,d):ds) ((a1,b1,c1,d1):ds1) db
         | ((a/=a1) && (b==d1 && b1==d)) = (a,a1):(nepotism' ds ((a1,b1,c1,d1):ds1) db)
         | otherwise = nepotism' ds ((a1,b1,c1,d1):ds1) db
                                           


-- 5.

rich :: DB -> [(String, String)]
rich db = getMax (maxVal moneyTups) moneyTups
        where moneyTups = genMoney db db
-- 6.

sorted_salaries	:: DB -> [String]
sorted_salaries db = fst(unzip(qsort(managerSalaryList db)))

sorted_rank :: DB -> [String]
sorted_rank db = fst(unzip(qsort(managerRankList (managerList db) db)))

sorted_worth :: DB -> [String]
sorted_worth db = fst((unzip(qsort(dividing managerSals managerRanks))))
              where 
                   managerSals = qsort1(managerSalaryList db)
                   managerRanks = qsort1(managerRankList (managerList db) db)

dividing [] [] = []
dividing ((a,b):xs) ((c,d):ys) 
             | a==c = (a,((fromInteger b)/(fromInteger (toInteger d)))):(dividing xs ys)
             | otherwise = error "Something is wrong"
-- 7.
{-
normalize :: DB -> DB
-}
