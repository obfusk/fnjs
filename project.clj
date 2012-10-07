(defproject fnjs "0.2.0-dev"
  :description  "fnjs - functional javascript"
  :url          "https://github.com/obfusk/fnjs"
  :licenses [ { :name "GPLv2", :distribution :repo
                :url "http://www.opensource.org/licenses/GPL-2.0" }
              { :name "EPLv1", :distribution :repo
                :url "http://www.opensource.org/licenses/EPL-1.0" } ]
  :source-path  "src"
  :main         fnjs.main
  :dependencies [ [ org.clojure/clojure "1.4.0" ] ] )
