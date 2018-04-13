# naver cafe

```ruby
require 'nokogiri'
require 'open-uri'

require 'rubygems'
require 'mechanize'

LOCATION = '/Users/hansumin/Crawller/public'
File.absolute_path(LOCATION)  
agent = Mechanize.new
page = agent.get('https://nid.naver.com/nidlogin.login?')

naver_form = page.form('frmNIDLogin')
naver_form.id = "naver_id"
naver_form.pw = "naver_pwd"
text = ""
page = agent.submit(naver_form)


cafe = agent.get("naver mobile url")

# title
text += "title:"+cafe.css("h2.tit").inner_text
title = cafe.css("h2.tit").inner_text

# image
cafe.css("img").each_with_index do |p,index|
    open(title+index.to_s+".png","wb") do |file|
        file << open(p["src"]).read
    end
end
text +="\n"
text +="content:"
# content
cafe.css("#postContent").each do |p|
    text += p.inner_text 
    text += "\n"
end

cafeBoard = File.new(title,"w")
cafeBoard.syswrite(text)
# File.write(LOCATION,text)
```



# 성경 구절

```ruby
require 'nokogiri'
require 'open-uri'

#url = "http://m.ibibles.net/quote10.htm"

#docs = Nokogiri::HTML(open(url))

#bookList = ""
#docs.css("select option").each do |p|
#    book = p.inner_text
#    # 성경 이름 출력 
#    bookList = book.split(" ")
#    # 내용 출력
#    puts bookList[1].to_s[1..-2]
#end
```

# 

# 유튜브 검색 리스트

```ruby

require('nokogiri')
require('open-uri')
require('rest-client')
    url = "#"
    result = Nokogiri::HTML(RestClient.get(url))
    
    result.encoding = 'utf-8'
    
    result = result.css("div#results ol.item-section li div")
    puts "결과"
    result.each do |p|
        puts
        if(p.attr('href').include?('watch'))
            puts p
            puts p
        end
  
    end
```

