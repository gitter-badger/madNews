(function ($){
	var NEWS_1_HTML='';
	$.ajax({
		url: "/media_files/news/html/1.html", 
		success: function(data) {
			NEWS_1_HTML=data;
		},
        async:   false
    });


	$.db_fixtures={
		top_news: [
			{'title':'SNP would let Labour govern but at a price, says Sturgeon','short_text':'“Ed Miliband can say what he wants right now, but he can’t deny reality, “ she told the BBC programme Today.', 'big_img':'/media_files/news/1.jpg', 'id':1},
			{'title':'Elveden has been a ‘grotesque’ waste of public money, says former Sun editor', 'shortText': 'A “truly grotesque” amount of public money has been spent pursuing journalists through the courts, a senior newspaper executive said today as he was formally acquitted at the Old Bailey.', 'big_img':'/media_files/news/2.jpg', 'id':2},
			{'title':'Retired general Frank Kitson sued over death during the Troubles', 'short_text': 'One of the most senior British army officers to have served in Northern Ireland is to be sued over the death of a Catholic man more than 40 years ago.', 'big_img':'/media_files/news/3.jpg', 'id':3},
			{'title':'Italian police unshroud face of boy Jesus', 'short_text': 'Italian police are accustomed to “ageing” photographs of wanted gangsters to help them track down fugitive.', 'big_img':'/media_files/news/4.jpg', 'id':4},
			//{'title':'', 'short_text': '', 'big_img':'/media_files/news/', 'id':},
		],
		tags:[ {'name':'Some tag 1','id':1, 'popularity':10},
				{'name':'Some tag 2','id':2, 'popularity':1},
				{'name':'Art', 'id':3, 'popularity':20},
				{'name':'Kittens', 'id':13, 'popularity':7},
				{'name':'Funny', 'id':4, 'popularity':6},
				{'name':'Some tag ...', 'id':5, 'popularity':18},
				{'name':'Some tag 3', 'id':6, 'popularity':1},
				{'name':'tag 10', 'id':13, 'popularity':11},
				{'name':'Some tag 4', 'id':7, 'popularity':2},
				{'name':'tag 11', 'id':14, 'popularity':11},
				{'name':'Some tag 5', 'id':8, 'popularity':4},
				{'name':'Some tag 6', 'id':9, 'popularity':20},
				{'name':'tag 9', 'id':12, 'popularity':11},
				{'name':'Some tag 7', 'id':10, 'popularity':9},
				{'name':'Some tag 8', 'id':11, 'popularity':11},
		],
		news:[
			{ 
				"id": 100,
				"title": "Finland drops depth charges in 'submarine' alert",
				"timestamp": 1305090400,
				"tags":[{'name':"HelloTag",'id':1},
						{'name':"Tag #2", 'id':2},
						{'name':"Some new tag", 'id':3}
					],
				"html": NEWS_1_HTML,
				"short_text":"The incident comes amid growing concern in the region over Russia's military exercises."
			},

			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'fixed_position':-1,'timestamp':1301090400,'comments_count':10, 'id':5},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'small_img':'/media_files/news/6.jpg', 'fixed_position':-1,'timestamp':1301000400,'comments_count':0,  'id':6},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':1,'timestamp':1301090400,'comments_count':20,  'id':7},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1302090400,'comments_count':13,  'id':8},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'fixed_position':-1,'timestamp':1301090400,'comments_count':3,  'id':15},
			{'title':'Greek hawk pulled from key debt talks', 'short_text':'Yanis Varoufakis, Greece’s flamboyant finance minister, was sidelined from key debt talks yesterday after growing international complaints about his intransigent style, which has left the country on the brink of bankruptcy.', 'small_img':'/media_files/news/7.jpg', 'fixed_position':-1,'timestamp':1301000400, 'id':6},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'fixed_position':-1,'timestamp':1301090400, 'id':16},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':3,'timestamp':1001090400,'comments_count':110,  'id':17},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1305090400, 'id':18},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'small_img':'/media_files/news/5.jpg', 'fixed_position':-1,'timestamp':1301090400,'comments_count':9, 'id':5},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'small_img':'/media_files/news/6.jpg', 'fixed_position':-1,'timestamp':1301000400, 'id':6},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':1,'timestamp':1301090400, 'id':7},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1302090400, 'id':8},
			{'title':'Greek hawk pulled from key debt talks', 'short_text':'Yanis Varoufakis, Greece’s flamboyant finance minister, was sidelined from key debt talks yesterday after growing international complaints about his intransigent style, which has left the country on the brink of bankruptcy.', 'small_img':'/media_files/news/7.jpg', 'fixed_position':-1,'timestamp':1301000400, 'id':6},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'fixed_position':-1,'timestamp':1301090400, 'id':15},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'fixed_position':-1,'timestamp':1301090400, 'id':16},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':3,'timestamp':1001090400, 'id':17},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1305090400, 'id':18},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'small_img':'/media_files/news/5.jpg', 'fixed_position':-1,'timestamp':1301090400,'comments_count':40, 'id':5},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'small_img':'/media_files/news/6.jpg', 'fixed_position':-1,'timestamp':1301000400, 'id':6},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':1,'timestamp':1301090400, 'id':7},
			{'title':'Greek hawk pulled from key debt talks', 'short_text':'Yanis Varoufakis, Greece’s flamboyant finance minister, was sidelined from key debt talks yesterday after growing international complaints about his intransigent style, which has left the country on the brink of bankruptcy.', 'small_img':'/media_files/news/7.jpg', 'fixed_position':-1,'timestamp':1301000400, 'id':6},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1302090400, 'id':8},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'fixed_position':-1,'timestamp':1301090400, 'id':15},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'fixed_position':-1,'timestamp':1301090400, 'id':16},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':3,'timestamp':1001090400, 'id':17},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1305090400, 'id':18},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'small_img':'/media_files/news/5.jpg', 'fixed_position':-1,'timestamp':1301090400,'comments_count':11, 'id':5},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'small_img':'/media_files/news/6.jpg', 'fixed_position':-1,'timestamp':1301000400, 'id':6},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':1,'timestamp':1301090400, 'id':7},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1302090400, 'id':8},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'fixed_position':-1,'timestamp':1301090400, 'id':15},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'fixed_position':-1,'timestamp':1301090400, 'id':16},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':3,'timestamp':1001090400, 'id':17},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1305090400, 'id':18},			
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'small_img':'/media_files/news/5.jpg', 'fixed_position':-1,'timestamp':1301090400,'id':5},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'small_img':'/media_files/news/6.jpg', 'fixed_position':-1,'timestamp':1301000400, 'comments_count':99, 'id':6},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':1,'timestamp':1301090400, 'id':7},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1302090400, 'id':8},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'fixed_position':-1,'timestamp':1301090400, 'id':15},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'fixed_position':-1,'timestamp':1301090400, 'id':16},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':3,'timestamp':1001090400, 'id':17},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1305090400, 'id':18},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'small_img':'/media_files/news/5.jpg', 'fixed_position':-1,'timestamp':1301090400,'id':5},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':1,'timestamp':1301090400, 'id':7},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1302090400, 'id':8},
			{'title':'Race for the Premier League', 'short_text':'Follow text and radio coverage as Bournemouth, who will be all but promoted to the top flight with a win, face Bolton.', 'fixed_position':-1,'timestamp':1301090400, 'id':15},
			{'title':'Oil price hovers at four-month high', 'short_text':'Oil price hovers at a four-and-a-half month high amid concerns over disruption to supplies from the Middle East.', 'fixed_position':-1,'timestamp':1301090400, 'id':16},
			{'title':'Buy your own piece of Bollywood', 'short_text':'Crazy about Indian film? Then you must be looking for these', 'fixed_position':3,'timestamp':1001090400, 'id':17},
			{'title':'Gift-giving for the super wealthy', 'short_text':'What do you get for the person who can buy almost anything? Meet the luxury concierges charged with accommodating lavish requests where the price is no object.', 'fixed_position':-1,'timestamp':1305090400, 'id':18},			



			//{'title':'', 'short_text':'', 'small_img':'', 'fixed_position':-1,'id':},
		],

		news_single:{ 
			"id": 100,
			"title": "Finland drops depth charges in 'submarine' alert",
			"timestamp": 1305090400,
			"tags":[{'name':"HelloTag",'id':1},
					{'name':"Tag #2", 'id':2},
					{'name':"Some new tag", 'id':3}
			],
			"html": NEWS_1_HTML,
			
		},
		admin_current_user:{
			"id":2,
			"username":"TestAdminUser",
			"permissions":['user-management',
							'news-add-edit-delete-own',
							'news-add-edit-delete-any',
							'news-edit-own']
		},
		admin_users:[
			{
				"id":3,
				"username":"Gerasev_Kirill",
				"permissions":['user-management',
							'news-add-edit-delete-own',
							'news-edit-any']
			},
			{
				"id":4,
				"username":"Andrey",
				"permissions":['news-add-edit-delete-own']
			},
			{
				"id":5,
				"username":"Anna_Poddubnaya",
				"permissions":['user-management',
							'news-edit-any'
								]
			},
			{
				"id":6,
				"username":"malgin",
				"permissions":[]
			},
			{
				"id":7,
				"username":"Daria_Velimovskaya",
				"permissions":['news-add-edit-delete-any']
			},
			{
				"id":8,
				"username":"Anya_Iermakova",
				"permissions":[
							'news-edit-any',
							]
			},
			{
				"id":9,
				"username":"Infinity",
				"permissions":[
							'news-add-edit-delete-own',
							]
			},


			],


	};


})(jQuery);
