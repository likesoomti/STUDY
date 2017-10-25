class CreateUsers < ActiveRecord::Migration[5.0]
  def change
    create_table :users do |t|
      t.string :username
      t.string :password
      t.string :salt
      t.string :email
      t.bolean :dm
      t.string :roles
      t.integer :reviews_count

      t.timestamps
    end
  end
end
