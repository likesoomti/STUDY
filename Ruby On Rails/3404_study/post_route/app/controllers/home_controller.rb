class HomeController < ApplicationController
  def index
  end

  def red
    @email = params[:email]
    @pwd = params[:password]
    @check = params[:check]
  end

  def blue
  end

  def green
  end

  def yellow
  end

  def color
    @result = params[:url]
  end
end
