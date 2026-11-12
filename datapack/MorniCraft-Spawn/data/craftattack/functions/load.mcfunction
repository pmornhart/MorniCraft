scoreboard objectives add mh_craftattack dummy
execute if score #built mh_craftattack matches 1 unless score #water mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #water mh_craftattack matches 1 run schedule function craftattack:fix_water 60t
execute if score #built mh_craftattack matches 1 unless score #hills mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #hills mh_craftattack matches 1 run schedule function craftattack:fix_hills 60t
execute if score #built mh_craftattack matches 1 unless score #center mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #center mh_craftattack matches 1 run schedule function craftattack:fix_center 60t
execute if score #built mh_craftattack matches 1 unless score #extras mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #extras mh_craftattack matches 1 run schedule function craftattack:fix_extras 60t
execute if score #built mh_craftattack matches 1 unless score #rainbow mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #rainbow mh_craftattack matches 1 run schedule function craftattack:fix_rainbow 60t
execute if score #built mh_craftattack matches 1 unless score #fountain mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #fountain mh_craftattack matches 1 run schedule function craftattack:fix_fountain 60t
execute if score #built mh_craftattack matches 1 unless score #benches mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #benches mh_craftattack matches 1 run schedule function craftattack:fix_benches 60t
execute if score #built mh_craftattack matches 1 unless score #renew mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #renew mh_craftattack matches 1 run schedule function craftattack:fix_renew 60t
execute if score #built mh_craftattack matches 1 unless score #reeds mh_craftattack matches 1 run forceload add -64 -64 64 64
execute if score #built mh_craftattack matches 1 unless score #reeds mh_craftattack matches 1 run schedule function craftattack:fix_reeds 60t
