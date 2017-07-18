/**
 * Created by tanyuan on 4/5/2017.
 */
export const API_ROOT = (process.env.NODE_ENV === 'production')
  ? 'http://139.196.240.34:7070/'
//:'http://42.159.252.240:7070/'
:(process.env.NODE_ENV === 'mock_development')?'http://localhost:3000/':'http://192.168.1.221:7070/'

export const CookieDomain = (process.env.NODE_ENV === 'production')
  ? ''
  :''
