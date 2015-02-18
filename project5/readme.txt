Xiaohui Chen
xc2388
xhchen0328@utexas.edu

Jiawei Guo
jg44347
tomwei1992@gmail.com

CS machine: charity.cs.utexas.edu
Full binary path: charity.cs.utexas.edu/usr/bin/java

Compile instruction
javac StrMatch.java
java StrMatch strings.txt somesource.txt results.txt
P.S: StopWatch.java is turned in for time checking.

Everything is working in this project

Test Cases:
Q=127

Pattern:
&After Republic of Texas Vice President Mirabeau B. Lamar visited the area&

&buffalo-hunter&

&during a buffalo-hunting expedition between 1837 and 1838, 
he proposed that the republic's capital then located in Houston, Texas, be relocated to the&

&"Father of Tejas"&


Output:
RK  MATCHED: After Republic of Texas Vice President Mirabeau B. Lamar visited the area
KMP MATCHED: After Republic of Texas Vice President Mirabeau B. Lamar visited the area
RK  FAILED: buffalo-hunter
KMP FAILED: buffalo-hunter
RK  MATCHED: during a buffalo-hunting expedition between 1837 and 1838, 
he proposed that the republic's capital then located in Houston, Texas, be relocated to the
KMP MATCHED: during a buffalo-hunting expedition between 1837 and 1838, 
he proposed that the republic's capital then located in Houston, Texas, be relocated to the
RK  FAILED: "Father of Tejas"
KMP FAILED: "Father of Tejas"

Source:
In the 1830s, pioneers began to settle the area in central Austin along the Colorado River. After Republic of Texas Vice President Mirabeau B. Lamar visited the area during a buffalo-hunting expedition between 1837 and 1838, 
he proposed that the republic's capital then located in Houston, Texas, be relocated to the area situated on the north bank of the Colorado River near the present-day Ann W. Richards Congress Avenue Bridge. In 1839, the site was officially chosen as the republic's new capital (the republic's seventh and final location) and was incorporated under the name Waterloo.

Shortly thereafter, the name was changed to Austin in honor of Stephen F. Austin, the "Father of Texas" and the republic's first secretary of state.

Running Time:
RK:         21012 ns
KMP:        12964 ns

RK:         42029 ns
KMP:        25298 ns

RK:         23831 ns
KMP:        17573 ns

RK:         40397 ns
KMP:        24883 ns

Q=47
pattern and result the same as the previous one

Running Time:
RK:         30676 ns
KMP:        12916 ns

RK:         42706 ns
KMP:        25182 ns

RK:         27028 ns
KMP:        17284 ns

RK:         40321 ns
KMP:        24801 ns


Q=997

pattern and result the same as the previous one

Running Time:
RK:         22643 ns
KMP:        12965 ns

RK:         42680 ns
KMP:        26297 ns

RK:         27236 ns
KMP:        17462 ns

RK:         41350 ns
KMP:        26061 ns

The running times for different Qs are similar. However, if Q is too small, then there will be large amount of unexpected collision. If Q is too large, then mod Q does not affect the values at all.

Massive Tesing
The pattern is stored in pattern.txt
The input file is 5921.txt
Result:
RK  MATCHED: DON QUIXOTE
KMP MATCHED: DON QUIXOTE
RK  MATCHED: "What does your worship think can have befallen my master,
Senor Licentiate Pero Perez?"
KMP MATCHED: "What does your worship think can have befallen my master,
Senor Licentiate Pero Perez?"
RK  MATCHED: This Web site includes information about Project Gutenberg-tm,
including how to make donations to the Project Gutenberg Literary
Archive Foundation, how to help produce our new eBooks, and how to
subscribe to our email newsletter to hear about new eBooks.

KMP MATCHED: This Web site includes information about Project Gutenberg-tm,
including how to make donations to the Project Gutenberg Literary
Archive Foundation, how to help produce our new eBooks, and how to
subscribe to our email newsletter to hear about new eBooks.

RK  MATCHED: for they of the Court are gaining the mastery in the tourney!" Called
away by this noise and outcry, they proceeded no farther with the
scrutiny of the remaining books, and so it is thought that "The Carolea,"
"The Lion of Spain," and "The Deeds of the Emperor," written by Don Luis
de Avila, went to the fire unseen and unheard; for no doubt they were
among those that remained, and perhaps if the curate had seen them they
would not have undergone so severe a sentence.

When they reached Don Quixote he was already out of bed, and was still
shouting and raving, and slashing and cutting all round, as wide awake as
if he had never slept.

They closed with him and by force got him back to bed, and when he had
become a little calm, addressing the curate, he said to him, "Of a truth,
Senor Archbishop Turpin, it is a great disgrace for us who call ourselves
the Twelve Peers, so carelessly to allow the knights of the Court to gain
the victory in this tourney, we the adventurers having carried off the
honour on the three former days."

"Hush, gossip," said the curate; "please God, the luck may turn, and what
is lost to-day may be won to-morrow; for the present let your worship
have a care of your health, for it seems to me that you are
over-fatigued, if not badly wounded."

"Wounded no," said Don Quixote, "but bruised and battered no doubt, for
that bastard Don Roland has cudgelled me with the trunk of an oak tree,
and all for envy, because he sees that I alone rival him in his
achievements. But I should not call myself Reinaldos of Montalvan did he
not pay me for it in spite of all his enchantments as soon as I rise from
this bed. For the present let them bring me something to eat, for that, I
feel, is what will be more to my purpose, and leave it to me to avenge
myself."

They did as he wished; they gave him something to eat, and once more he
fell asleep, leaving them marvelling at his madness.

That night the housekeeper burned to ashes all the books that were in the
yard and in the whole house; and some must have been consumed that
deserved preservation in everlasting archives, but their fate and the
laziness of the examiner did not permit it, and so in them was verified
the proverb that the innocent suffer for the guilty.

One of the remedies which the curate and the barber immediately applied
to their friend's disorder was to wall up and plaster the room where the
books were, so that when he got up he should not find them (possibly the
cause being removed the effect might cease), and they might say that a
magician had carried them off, room and all; and this was done with all
despatch. Two days later Don Quixote got up, and the first thing he did

KMP MATCHED: for they of the Court are gaining the mastery in the tourney!" Called
away by this noise and outcry, they proceeded no farther with the
scrutiny of the remaining books, and so it is thought that "The Carolea,"
"The Lion of Spain," and "The Deeds of the Emperor," written by Don Luis
de Avila, went to the fire unseen and unheard; for no doubt they were
among those that remained, and perhaps if the curate had seen them they
would not have undergone so severe a sentence.

When they reached Don Quixote he was already out of bed, and was still
shouting and raving, and slashing and cutting all round, as wide awake as
if he had never slept.

They closed with him and by force got him back to bed, and when he had
become a little calm, addressing the curate, he said to him, "Of a truth,
Senor Archbishop Turpin, it is a great disgrace for us who call ourselves
the Twelve Peers, so carelessly to allow the knights of the Court to gain
the victory in this tourney, we the adventurers having carried off the
honour on the three former days."

"Hush, gossip," said the curate; "please God, the luck may turn, and what
is lost to-day may be won to-morrow; for the present let your worship
have a care of your health, for it seems to me that you are
over-fatigued, if not badly wounded."

"Wounded no," said Don Quixote, "but bruised and battered no doubt, for
that bastard Don Roland has cudgelled me with the trunk of an oak tree,
and all for envy, because he sees that I alone rival him in his
achievements. But I should not call myself Reinaldos of Montalvan did he
not pay me for it in spite of all his enchantments as soon as I rise from
this bed. For the present let them bring me something to eat, for that, I
feel, is what will be more to my purpose, and leave it to me to avenge
myself."

They did as he wished; they gave him something to eat, and once more he
fell asleep, leaving them marvelling at his madness.

That night the housekeeper burned to ashes all the books that were in the
yard and in the whole house; and some must have been consumed that
deserved preservation in everlasting archives, but their fate and the
laziness of the examiner did not permit it, and so in them was verified
the proverb that the innocent suffer for the guilty.

One of the remedies which the curate and the barber immediately applied
to their friend's disorder was to wall up and plaster the room where the
books were, so that when he got up he should not find them (possibly the
cause being removed the effect might cease), and they might say that a
magician had carried them off, room and all; and this was done with all
despatch. Two days later Don Quixote got up, and the first thing he did

RK  FAILED: Things are said to be named 'equivocally'
KMP FAILED: Things are said to be named 'equivocally'
RK  FAILED: one thing is predicated of another
KMP FAILED: one thing is predicated of another
RK  FAILED: such parts are not substances
KMP FAILED: such parts are not substances
RK  FAILED: differentiae
KMP FAILED: differentiae
RK  FAILED: By being 'present in a subject' I do not mean present as parts
are present in a whole, but being incapable of existence apart
from the said subject.
KMP FAILED: By being 'present in a subject' I do not mean present as parts
are present in a whole, but being incapable of existence apart
from the said subject.
RK  FAILED: Substance, again, does not appear to admit of variation of
degree. I do not mean by this that one substance cannot be more
or less truly substance than another, for it has already been
stated' that this is the case; but that no single substance
admits of varying degrees within itself. For instance, one
particular substance, 'man', cannot be more or less man either
than himself at some other time or than some other man. One man
cannot be more man than another, as that which is white may be
more or less white than some other white object, or as that which
is beautiful may be more or less beautiful than some other
beautiful object. The same quality, moreover, is said to subsist
in a thing in varying degrees at different times. A body, being
white, is said to be whiter at one time than it was before, or,
being warm, is said to be warmer or less warm than at some other
time. But substance is not said to be more or less that which it
is: a man is not more truly a man at one time than he was before,
nor is anything, if it is substance, more or less what it is.
Substance, then, does not admit of variation of degree.
KMP FAILED: Substance, again, does not appear to admit of variation of
degree. I do not mean by this that one substance cannot be more
or less truly substance than another, for it has already been
stated' that this is the case; but that no single substance
admits of varying degrees within itself. For instance, one
particular substance, 'man', cannot be more or less man either
than himself at some other time or than some other man. One man
cannot be more man than another, as that which is white may be
more or less white than some other white object, or as that which
is beautiful may be more or less beautiful than some other
beautiful object. The same quality, moreover, is said to subsist
in a thing in varying degrees at different times. A body, being
white, is said to be whiter at one time than it was before, or,
being warm, is said to be warmer or less warm than at some other
time. But substance is not said to be more or less that which it
is: a man is not more truly a man at one time than he was before,
nor is anything, if it is substance, more or less what it is.
Substance, then, does not admit of variation of degree.
RK  MATCHED: deliberately setting himself to expound
KMP MATCHED: deliberately setting himself to expound
RK  MATCHED: I deserve reproach for being beautiful
KMP MATCHED: I deserve reproach for being beautiful
RK  MATCHED: she watches over her honour
KMP MATCHED: she watches over her honour
RK  MATCHED: And Love hath made her his ally
KMP MATCHED: And Love hath made her his ally
RK  MATCHED: "If your worship is angry," replied Sancho, "I will hold my tongue and
leave unsaid what as a good squire I am bound to say, and what a good
servant should tell his master."
KMP MATCHED: "If your worship is angry," replied Sancho, "I will hold my tongue and
leave unsaid what as a good squire I am bound to say, and what a good
servant should tell his master."
RK  MATCHED: While this was passing between the ladies of the castle and Don Quixote,
the curate and the barber bade farewell to Don Fernando and his
companions, to the captain, his brother, and the ladies, now all made
happy, and in particular to Dorothea and Luscinda. They all embraced one
another, and promised to let each other know how things went with them,
and Don Fernando directed the curate where to write to him, to tell him
what became of Don Quixote, assuring him that there was nothing that
could give him more pleasure than to hear of it, and that he too, on his
part, would send him word of everything he thought he would like to know,
about his marriage, Zoraida's baptism, Don Luis's affair, and Luscinda's
return to her home. The curate promised to comply with his request
carefully, and they embraced once more, and renewed their promises.

The landlord approached the curate and handed him some papers, saying he
had discovered them in the lining of the valise in which the novel of
"The Ill-advised Curiosity" had been found, and that he might take them
all away with him as their owner had not since returned; for, as he could
not read, he did not want them himself. The curate thanked him, and
opening them he saw at the beginning of the manuscript the words, "Novel
of Rinconete and Cortadillo," by which he perceived that it was a novel,
and as that of "The Ill-advised Curiosity" had been good he concluded
this would be so too, as they were both probably by the same author; so
he kept it, intending to read it when he had an opportunity. He then
mounted and his friend the barber did the same, both masked, so as not to
be recognised by Don Quixote, and set out following in the rear of the
cart. The order of march was this: first went the cart with the owner
leading it; at each side of it marched the officers of the Brotherhood,
as has been said, with their muskets; then followed Sancho Panza on his
ass, leading Rocinante by the bridle; and behind all came the curate and
the barber on their mighty mules, with faces covered, as aforesaid, and a
grave and serious air, measuring their pace to suit the slow steps of the
oxen. Don Quixote was seated in the cage, with his hands tied and his
feet stretched out, leaning against the bars as silent and as patient as
if he were a stone statue and not a man of flesh. Thus slowly and
silently they made, it might be, two leagues, until they reached a valley
which the carter thought a convenient place for resting and feeding his
oxen, and he said so to the curate, but the barber was of opinion that
they ought to push on a little farther, as at the other side of a hill
which appeared close by he knew there was a valley that had more grass
and much better than the one where they proposed to halt; and his advice
was taken and they continued their journey.
KMP MATCHED: While this was passing between the ladies of the castle and Don Quixote,
the curate and the barber bade farewell to Don Fernando and his
companions, to the captain, his brother, and the ladies, now all made
happy, and in particular to Dorothea and Luscinda. They all embraced one
another, and promised to let each other know how things went with them,
and Don Fernando directed the curate where to write to him, to tell him
what became of Don Quixote, assuring him that there was nothing that
could give him more pleasure than to hear of it, and that he too, on his
part, would send him word of everything he thought he would like to know,
about his marriage, Zoraida's baptism, Don Luis's affair, and Luscinda's
return to her home. The curate promised to comply with his request
carefully, and they embraced once more, and renewed their promises.

The landlord approached the curate and handed him some papers, saying he
had discovered them in the lining of the valise in which the novel of
"The Ill-advised Curiosity" had been found, and that he might take them
all away with him as their owner had not since returned; for, as he could
not read, he did not want them himself. The curate thanked him, and
opening them he saw at the beginning of the manuscript the words, "Novel
of Rinconete and Cortadillo," by which he perceived that it was a novel,
and as that of "The Ill-advised Curiosity" had been good he concluded
this would be so too, as they were both probably by the same author; so
he kept it, intending to read it when he had an opportunity. He then
mounted and his friend the barber did the same, both masked, so as not to
be recognised by Don Quixote, and set out following in the rear of the
cart. The order of march was this: first went the cart with the owner
leading it; at each side of it marched the officers of the Brotherhood,
as has been said, with their muskets; then followed Sancho Panza on his
ass, leading Rocinante by the bridle; and behind all came the curate and
the barber on their mighty mules, with faces covered, as aforesaid, and a
grave and serious air, measuring their pace to suit the slow steps of the
oxen. Don Quixote was seated in the cage, with his hands tied and his
feet stretched out, leaning against the bars as silent and as patient as
if he were a stone statue and not a man of flesh. Thus slowly and
silently they made, it might be, two leagues, until they reached a valley
which the carter thought a convenient place for resting and feeding his
oxen, and he said so to the curate, but the barber was of opinion that
they ought to push on a little farther, as at the other side of a hill
which appeared close by he knew there was a valley that had more grass
and much better than the one where they proposed to halt; and his advice
was taken and they continued their journey.
RK  MATCHED: She fights in me and conquers in me
KMP MATCHED: She fights in me and conquers in me
RK  MATCHED: whoreson scoundrel
KMP MATCHED: whoreson scoundrel
RK  MATCHED: Sancho
KMP MATCHED: Sancho
RK  MATCHED: The Project Gutenberg EBook of The History of Don Quixote, Vol. I, Complete
by Miguel de Cervantes

KMP MATCHED: The Project Gutenberg EBook of The History of Don Quixote, Vol. I, Complete
by Miguel de Cervantes

Running Time:
RK:         48816 ns
KMP:        23873 ns

RK:      10447074 ns
KMP:      4267917 ns

RK:      20149200 ns
KMP:      3942824 ns

RK:       3895471 ns
KMP:       777419 ns

RK:      22811957 ns
KMP:      3464193 ns

RK:      22820666 ns
KMP:      4721693 ns

RK:      22822539 ns
KMP:      4327592 ns

RK:      22820570 ns
KMP:      4059299 ns

RK:      22961693 ns
KMP:      3437787 ns

RK:      23270102 ns
KMP:      6065414 ns

RK:       1482546 ns
KMP:       273805 ns

RK:       5867171 ns
KMP:       897047 ns

RK:       5237923 ns
KMP:       988508 ns

RK:       5975294 ns
KMP:       907773 ns

RK:      20251142 ns
KMP:      3105666 ns

RK:      20600149 ns
KMP:      3221652 ns

RK:      13157118 ns
KMP:      1994229 ns

RK:      13161403 ns
KMP:      2209530 ns

RK:       1104301 ns
KMP:       171886 ns

RK:         12690 ns
KMP:         6416 ns



Description:
StrMatch.java
This program takes 3 arguments, which are pattern file name, source file name and output file name. It also makes use of StopWatch.java (downloaderd from Internet) to test the running time of our program. 

First of all, an array list of patterns is generated using getPatterns method. Then an array of chars is generated. This array stores the characters in the source file. When creating the previous two arrays, any new line characters is changed to '\n' for correctness and simplicity. Then a loop iterate through all patterns and in each loop RabinKarp method and KnuthMorrisPratt method are called respectively. Those two methods return true when there is a match, false otherwise. The matching results are written in the output file and the running times are printed out.

Rabin-Karp
The RabinKarp method implements Rabin-Karp algorithm. For simplisity, the hashing value is the concatenations of the ASCII values % Q. We choose Q as 127. If the hashing value of pattern equals to the one with the selected string from the source file, then this method returns true. Otherwise this method continues until the end of the source file.


KMP algorithm
KnuthMorrisPratt method implements this algorithm. An array of cores of prefixes is generated using precomputeCoresOfPrefixes method. Then a while loop iterates through the source string to find a match. If match fails, the starting index for next checking string is fetched accorfing to the core index. If the string completely maches the pattern, this method returns true.

