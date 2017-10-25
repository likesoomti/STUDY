class CreateAuthers < ActiveRecord::Migration[5.0]
  def change
    create_table :authers do |t|
      t.references :user, foreign_key: true
      t.string :name
      t.date :birth
      t.text :address
      t.string :ctype
      t.binary :photo

      t.timestamps
    end
  end
end
