import styles from "./Footer.module.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse, faHeart, faUser, faGear } from '@fortawesome/free-solid-svg-icons';

export default function Footer() {
  const menuItems = [
    { href: '/dashboard', icon: faHouse, label: '피드' },
    { href: '/team/fav', icon: faHeart, label: '응원팀' },
    { href: '/players/fav', icon: faUser, label: '선수' },
    { href: '/profile', icon: faGear, label: '설정' },
  ];

  return (
      <div className={styles.footer}>
        <nav className={styles.navigation}>
          <ul>
            {menuItems.map((item, index) => (
                <li key={index}>
                  <a href={item.href}>
                    <div className={styles.icon}>
                      <FontAwesomeIcon className={styles.ii} icon={item.icon} />
                    </div>
                    <div className={styles.linkTo}>{item.label}</div>
                  </a>
                </li>
            ))}
          </ul>
        </nav>
      </div>
  );
}
