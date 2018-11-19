package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma tr?n ch?a thông tin b?n ??, m?i ph?n t? l?u giá tr? kí t? ??c ???c
	 * t? ma tr?n b?n ?? trong t?p c?u hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		 try {
                        
			URL absPath = FileLevelLoader.class.getResource("/levels" + "/Level"+2+".txt");
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(absPath.openStream()));

			String data = in.readLine();
			StringTokenizer tokens = new StringTokenizer(data);
			
			_level = Integer.parseInt(tokens.nextToken());
			_height = Integer.parseInt(tokens.nextToken());
			_width = Integer.parseInt(tokens.nextToken());
                        //System.out.println(data);
			String []_lineTiles = new String[_height];
			_map = new char[_height][_width];
			for(int i = 0; i < _height; ++i) {
				_lineTiles[i] = in.readLine().substring(0, _width);
                                for (int j = 0; j < _width; j++) {
                                    _map[i][j] = _lineTiles[i].charAt(j);
                                    System.out.print(_map[i][j]);
                            }
                                System.out.println("");
 			}
			
			in.close();
		} catch (IOException e) {
			//throw new LoadLevelException("Error loading level " + path, e);
		}
	}

	@Override
		
	public void createEntities() {
		for (int x = 0; x < _width; x++) {
			for (int y = 0; y < _height; y++) {
                                int pos = x + y*_width;
                            switch (_map[y][x]) {
                                case '*':
                                    _board.addEntity(pos,new Brick(x,y,Sprite.brick));
                                    break;
                                case '#':
                                    _board.addEntity(pos,new Wall(x,y,Sprite.wall));
                                    break;
                                case 'x':
                                    _board.addEntity(pos,new Portal(x,y,Sprite.portal));
                                    break;
                                case 'p':
                                    _board.addCharacter( new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
                                    Screen.setOffset(0, 0);
                                    _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                                    break;
                                case '1':
                                    _board.addCharacter( new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
                                    Screen.setOffset(0, 0);
                                    _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                                    break;
                                case '2':
                                    _board.addCharacter( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
                                    Screen.setOffset(0, 0);
                                    _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                                    break;
                                default:
                                    _board.addEntity(pos,new Grass(x,y,Sprite.grass));
                                    break;
                            }
		}
                }
        }}

        

		
                
            
	
		// TODO: sau khi t?o xong, g?i _board.addEntity() ?? thêm Entity vào game

		// TODO: ph?n code m?u ? d??i ?? h??ng d?n cách thêm các lo?i Entity vào game
		// TODO: hãy xóa nó khi hoàn thành ch?c n?ng load màn ch?i t? t?p c?u hình
		// thêm Wall
//                                        1 13 31
//                                        ###############################
//                                        #p     ** *  1 * 2 *  * * *   #
//                                        # # # #*# # #*#*# # # #*#*#*# #
//                                        #  x*     ***  *  1   * 2 * * #
//                                        # # # # # #*# # #*#*# # # # #*#
//                                        #f         x **  *  *   1     #
//                                        # # # # # # # # # #*# #*# # # #
//                                        #*  *      *  *      *        #
//                                        # # # # #*# # # #*#*# # # # # #
//                                        #*    **  *       *           #
//                                        # #*# # # # # # #*# # # # # # #
//                                        #           *   *  *          #
//                                        ###############################
               
//		for (int x = 0; x < 20; x++) {
//			for (int y = 0; y < 20; y++) {
//				int pos = x + y * _width;
//				Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, sprite));
//			}
//		}
//
//		// thêm Bomber
//		int xBomber = 1, yBomber = 1;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//                int xxBomber = 2, yyBomber = 2;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xxBomber), Coordinates.tileToPixel(yyBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xxBomber + yyBomber * _width, new Grass(xxBomber, yyBomber, Sprite.grass));
//
//		// thêm Enemy
//		int xE = 2, yE = 10;
//		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//		// thêm Brick
//		int xB = 3, yB = 1;
//		_board.addEntity(xB + yB * _width,
//				new LayeredEntity(xB, yB,
//					new Grass(xB, yB, Sprite.grass),
//					new Brick(xB, yB, Sprite.brick)
//				)
//		);
//
//		// thêm Item kèm Brick che ph? ? trên
//		int xI = 1, yI = 2;
//		_board.addEntity(xI + yI * _width,
//				new LayeredEntity(xI, yI,
//					new Grass(xI ,yI, Sprite.grass),
//					new SpeedItem(xI, yI, Sprite.powerup_flames),
//					new Brick(xI, yI, Sprite.brick)
//				)
//		);
//	}
//
//}


//}
