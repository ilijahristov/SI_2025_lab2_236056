# SI_2025_lab2_236056

# 2. 
# Za checkCart CFG
dot -Tpng checkCart.dot -o checkCart.png
git add checkCart.png
git commit -m "Add checkCart Control Flow Graph"
git push origin main

# 3.
# Ciklomatska kompleksnost
M = V - N + 2P
V = 19
N = 18
P = 1
M = 19 - 18 + 2(1)
M = 3

# 4.
# Every statement criteria
Start →
if allItems == null? → no →
for each item →
if item.getName null or empty? → no →
if price > 300 or discount > 0 or quantity > 10? → yes →
sum -= 30 →
if discount > 0? → yes →
sum += discounted price →
(loop ends) →
if cardNumber != null and length == 16? → yes →
for each char in cardNumber →
if char allowed? → yes →
return sum

# Null Exception
Start →
if allItems == null? → yes →
throw Exception

# Invalid char exception
Start →
if allItems == null? → no →
for each item →
if item.getName null or empty? → no →
if price > 300... → no →
if discount > 0? → no →
sum += full price →
(loop ends) →
if cardNumber != null and length == 16? → yes →
for each char →
if char allowed? → no →
throw Exception

# 5.
Min broj test slucai spored Multiple Condition se 8 za uslovot if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

Bidejki uslovot e forma A ili B ili C so vistinitosna tabla se dobivaat 8 kombinacii