# coding: utf-8
class HelloController < ApplicationController
	def index
		render text: 'Hello world..!'
	end

	def view
		@msg = " hello world vies "
		render :show
		
	end
	def show
	end
	def new
	end
	def list

		@books = Book.all
		
	end
end
