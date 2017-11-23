# Model

## Basic

**MODEL**은 애플리케이션의 정보(데이터)를 나타냅니다. **rails** 에서는 **`Active Record`** Library를 통해 모델을 구성합니다.

### command line

#### 모델 설치
```
$ rails g model 모델이름 컬럼이름:타입
```
###### example
```
$ rails g model post title:string content:text
```
#### 모델 삭제
```
$ rails d model 모델이름
```
###### example
```
$ rails d model post 
```

#### 데이터베이스 저장
```
$ rails db:migrate
```

#### 데이터베이스 삭제
```
$ rails db:drop
```

### seed 생성하기
**seed**는 데이터베이스에 정보를 미리 저장할 수 있습니다. test를 위해 더미 값을 등록하거나, 미리 존재하는 정보들을 넣을 때 사용합니다.

#### 경로
`app/db/seeds.rb`
####  example
- 기본 설정 
```ruby
   post = POST.create([{ title: 'Star Wars' }, { content: 'Lord of the Rings' }])
```

- `Faker gem`을 이용한 Dumy 넣기

    faker gem에 대한 내용은 
    https://github.com/stympy/faker
    에서 확인할 수 있습니다.

```ruby
3.times{
    post = POST.new(
        title: Faker::Internet.email,
        content: Faker::HarryPotter.quote
      )    
    user.save
}
```
- `File` 을 이용한 Dumy 넣기 
파일의 경로를 지정하고, 제목과 이름을 동일시 해서 넣어주면 됩니다.
```ruby
require 'csv'

csv_text = File.read(Rails.root.join('lib', 'seeds', 'test.csv'))
csv = CSV.parse(csv_text, :headers => true, :encoding => 'UTF-8')
csv.each do |row|
  t = Store.new
  t.id = row['id']
  t.title = row['title']
  t.content = row['content']
  t.save
end
```