/**
 * Created by tanyuan on 4/5/2017.
 */
export const API_ROOT = (process.env.NODE_ENV === 'production')
  ? 'http://10.20.40.105:7070/'
//:'http://42.159.252.240:7070/'
:(process.env.NODE_ENV === 'mock_development')?'http://localhost:3000/':'http://42.159.224.87:7171/'

export const CookieDomain = (process.env.NODE_ENV === 'production')
  ? ''
  :''
