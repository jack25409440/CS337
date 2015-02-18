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
salary db "Root" = 100000
salary [] name = 0
salary ((a,_,b,_):ds) name 
                 | a==name = b
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

largest [] db sal = True
largest (x:xs) db sal
              | ((salary db x) < sal) = True && largest xs db sal
              | otherwise= False

common_elem [] bs = []
common_elem (a:as) bs
	| (a == "Root") = common_elem as bs
	| (a `elem` bs) = a:(common_elem as bs)
	| otherwise = common_elem as bs

add_to_list [] ds = ds
add_to_list (m:ms) ds
	| (m `elem` ds) = add_to_list ms ds
	| otherwise = m:(add_to_list ms ds)

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

grossly_overpaid' :: DB -> DB -> [String]
grossly_overpaid' [] db = []
grossly_overpaid' ((a,b,c,d):ds) db
                   | (largest supermanagers db c) = a:(grossly_overpaid' ds db)
                   | otherwise = grossly_overpaid' ds db
                   where
                      supermanagers = (getSuper db a)

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
                             | (b `elem` (getSuper db a)) = b:(spouse_manager_super' ds db)
                             | otherwise = spouse_manager_super' ds db

-- 3.




super_manager :: DB -> [String]
super_manager db = super_manager' db db

super_manager' [] db = []
super_manager' ((a,b,c,d):ds) db = add_to_list (common_elem ass bss) (super_manager' ds db)
	where   ass = getSuper db a
		bss = getSuper db b
               

-- 4.

nepotism :: DB -> [(String, String)]
nepotism db = nepotism' db db db

nepotism' xs [] db = []
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

normalize :: DB -> DB
normalize db = normalize' db ["Root"]

normalize' [] ms = []
normalize' ((a,b,c,d):ds) ms
	| (d `elem` ms) = (a,b,c,d):(normalize' ds (a:ms))
	| otherwise = normalize' (ds++[(a,b,c,d)]) ms

main= do
      print("overpaid:")
      print(overpaid database0)
      print(overpaid database1)
      print(overpaid database2)
      print(overpaid database3)
      print("")
      print("grossly_overpaid:")
      print(grossly_overpaid database0)
      print(grossly_overpaid database1)
      print(grossly_overpaid database2)
      print(grossly_overpaid database3)
      print("")
      print("spouse_manager:")
      print(spouse_manager database0)
      print(spouse_manager database1)
      print(spouse_manager database2)
      print(spouse_manager database3)
      print("")
      print("spouse_manager_super:")
      print(spouse_manager_super database0)
      print(spouse_manager_super database1)
      print(spouse_manager_super database2)
      print(spouse_manager_super database3)
      print("")
      print("super_manager:")
      print(super_manager database0)
      print(super_manager database1)
      print(super_manager database2)
      print(super_manager database3)
      print("")
      print("nepotism:")
      print(nepotism database0)
      print(nepotism database1)
      print(nepotism database2)
      print(nepotism database3)
      print("")
      print("rich:")
      print(rich database0)
      print(rich database1)
      print(rich database2)
      print(rich database3)
      print("")
      print("sorted_salaries:")
      print(sorted_salaries database0)
      print(sorted_salaries database1)
      print(sorted_salaries database2)
      print(sorted_salaries database3)
      print("")
      print("sorted_rank:")
      print(sorted_rank database0)
      print(sorted_rank database1)
      print(sorted_rank database2)
      print(sorted_rank database3)
      print("")
      print("sorted_worth:")
      print(sorted_worth database0)
      print(sorted_worth database1)
      print(sorted_worth database2)
      print(sorted_worth database3)
      print("")
      print("normalize:")
      print(normalize database0)
      print(normalize database1)
      print(normalize database2)
      print(normalize database3)
      print("")
