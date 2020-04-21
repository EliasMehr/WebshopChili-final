insert into product(scoville_strength,description,image_url,price,name)
select "1 000","Red chili","redchili.png",40,"Red chili"
union
select "1 500 000","The Carolina Reaper is officially the World's Hottest Pepper. It has a sweet and fruity flavor, that is before the intense heat kicks in.","calireaper.png",40,"California Reaper"
union
select "1 000 000","The ghost pepper (aka the Bhut Jolokia) is one of the hottest pepper in the world, topping over 1 Million Scoville Heat Units.","nagajolokia.png",40,"Naga jolokia"
union
select "8 000","The jalapeno is a medium-sized chili pepper pod type cultivar of the species Capsicum annuum","jalapeno.png",40,"Jalapeno"
union
select "5 000","Manzano peppers are smaller, with an apple-like shape, but spicy.","manzano.png",40,"Manzano"
union
select "9 000","Cayenne peppers are actually hotter when they are more mature, in their red form.","cayenne.png",40,"Cayenne"
union
select "8 000","Serrano peppers are high in vitamin C and potassium. They also have some calcium, iron, zinc, selenium, folate, vitamin A, vitamin K, and choline.","serrano.png",40,"Serrano"
union
select "9 000","Piripiri is also known as African red Devil and African Bird''s Eye. Fact: African bird''s eye chillies are devilishly sharp.","piripiri.png",40,"Piri piri"
union
select "80 000","As tasty as Cats are Cute, in other words TASTY AS ****","devilcat.png",40,"DevilCat"
union
select "1 000","Grows in the mountains of Busan, legends says that this Chili will transform you into a Cat.","batcho.png",40,"Batcho"
union
select "7 000","Hell","hell.png",45,"Hell"
union
select "2 000","When Jobs didn't eat fruit he ate his Chili.","stevejobs.png",42,"SteveJobs"
union
select "9 000","Open source Chili","tomcatchili.png",41,"TomCat Chili"
union
select "4 000","This chili Will give you powerfull forces to create applications","ejbchili.png",50,"EJB Chili"
union
select "7 000 0","The Chili that helped Gosling build Java, The chili diet is the new fruitdiet","jamesgosling.png",70,"The James Gosling";


insert into role(type, discount_multiplier)
select "Admin",1
union
select "Customer",1
union
select "Premium",1;