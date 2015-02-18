Xiaohui Chen
xc2388
xhchen0328@utexas.edu

Jiawei Guo
jg44347
tomwei1992@gmail.com

CS machine: charity.cs.utexas.edu
Full binary path: charity.cs.utexas.edu/usr/bin/ghc

Compile Instruction:
ghci
:l project4
main

Test Code:
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

Result:
"overpaid:"
["Virginia Dare","Buster Keaton","Vanessa Redgrave"]
[]
["Carol","Eric"]
["Carol","Fran","Dan","Eric"]
""
"grossly_overpaid:"
["Vanessa Redgrave"]
[]
["Carol","Eric"]
["Carol","Fran","Dan","Eric"]
""
"spouse_manager:"
["Vanessa Redgrave"]
[]
["Alex"]
["Alex"]
""
"spouse_manager_super:"
["Edna Millay","Vanessa Redgrave"]
[]
["Dan","Alex","Carol"]
["Alex"]
""
"super_manager:"
["Virginia Dare","Edna Millay","James Joyce"]
[]
["Carol","Bob","Alex"]
[]
""
"nepotism:"
[("Ingrid Joyce","Laurence Sterne"),("Laurence Sterne","Ingrid Joyce")]
[]
[]
[]
""
"rich:"
[("Lana Turner","Buster Keaton"),("Virginia Dare","Laurence Sterne")]
[]
[("Carol","Eric")]
[("Carol","Eric")]
""
"sorted_salaries:"
["Vanessa Redgrave","Virginia Dare","Edna Millay","James Joyce","Ingrid Joyce"]
[]
["Eric","Carol","Dan","Alex","Bob"]
["Alex"]
""
"sorted_rank:"
["Virginia Dare","James Joyce","Vanessa Redgrave","Ingrid Joyce","Edna Millay"]
[]
["Dan","Carol","Alex","Eric","Bob"]
["Alex"]
""
"sorted_worth:"
["Vanessa Redgrave","Edna Millay","Ingrid Joyce","Virginia Dare","James Joyce"]
[]
["Eric","Carol","Dan","Alex","Bob"]
["Alex"]
""
"normalize:"
[("James Joyce","Ingrid Joyce",60000,"Root"),("Vanessa Redgrave","Michael Redgrave",110000,"James Joyce"),("Michael Redgrave","Vanessa Redgrave",40000,"Vanessa Redgrave"),("Edna Millay","Ted Hughes",70000,"Root"),("Laurence Sterne","Virginia Dare",60000,"James Joyce"),("Virginia Dare","Laurence Sterne",100000,"Edna Millay"),("Ingrid Joyce","James Joyce",60000,"Virginia Dare"),("Lana Turner","Buster Keaton",80000,"Virginia Dare"),("Ted Hughes","Edna Millay",70000,"Virginia Dare"),("Buster Keaton","Lana Turner",80000,"Ingrid Joyce")]
[]
[("Alex","Bob",100000,"Root"),("Bob","Alex",100000,"Alex"),("Carol","Eric",200000,"Bob"),("Dan","Fran",150000,"Carol"),("Eric","Carol",300000,"Dan"),("Fran","Dan",200000,"Eric")]
[("Carol","Eric",200000,"Root"),("Fran","Dan",200000,"Root"),("Dan","Fran",150000,"Root"),("Alex","Bob",100000,"Root"),("Eric","Carol",300000,"Root"),("Bob","Alex",100000,"Alex")]
""

Everything is working in this project

Function descriptions:
The functions are all implemented by recursion

overpaid
overpaid' is called, which takes two lists as arguments. The first is for iteration while the second one is the original database. It calls salary function, which resturns the salary of a specified name's manager. Then a comparison of salaries to determine if this emplyee is overpaid.

grossly_overpaid
grossly_overpaid' function is called. The function getSuper generates a list of a specified person's super managers. Then largest is called to get the largest paid super manager. Through comparison we can determine whether the employee is grossly overpaid

sopuse_manager
This function checks each touple to determine whether Spouse==Manager.

spouse_manager_super
For each touple, check whether the spouse is in the super manager list generated by getSuper function.

super_manager
For each tuple, check the super manager lists of both the employee and the spouse to find the common super manager.

nepotism
nepotism', which takes 3 arguments, is called. In this function,each tuples in the first 2 arguments(lists) compare with each other to determine if there is nepotism. Because the same pair of tuples compared twice, the qualified pairs would appear twice as required.

rich
Function genMoney is called to get a list of tuples of the names of the couple and their total salry. Then getMax is use to get the richest couple

sorted_salaires
List of tuples of (Employee, Salary) is generated using managerSalaryList function. Then qsort is used to sort the tuples according to the salary. Then fst is called to the the list of first elements

sorted_rank
List of tuples of (Employee, Rank) is generated using managerRankList function. Then qsort is used to sort the tuples according to the rank. Then fst is called to the the list of first elements

sorted_worth
Fuctions managerSalaryList and managerRankList are called to get 2 lists of tuples. Then qsort1 is called to sort the two lists according to the first elements and so the same name would apper at the same position in two lists. After that dividing is called to get the worth of each manager. Finally the list is sorsted and fst gets a list of first elements

normalize
Function normalize', which takes 2 arguments is called. If the first element in the first list is in the second list, then the tuple is added to the final list and the second list. Otherwise the tuple is added to the last of the first list. The function iterates recursively.
